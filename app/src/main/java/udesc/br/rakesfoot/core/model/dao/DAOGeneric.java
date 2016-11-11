package udesc.br.rakesfoot.core.model.dao;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;
import udesc.br.rakesfoot.core.util.BeanUtils;
import udesc.br.rakesfoot.core.util.StringUtils;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;
import udesc.br.rakesfoot.game.model.TeamClassification;

import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Samuel Felício Adriano
 * @param <DAOEntity>
 */
@SuppressWarnings("Since15")
public abstract class DAOGeneric<DAOEntity extends udesc.br.rakesfoot.core.model.Entity> implements Persistible<DAOEntity> {

    protected static final String DAO_PATH    = "udesc.br.rakesfoot.game.model.dao.sqlite.",
                                  ENTITY_PATH = "udesc.br.rakesfoot.game.model.",
                                  PREFIX      = "SqliteDao";

    protected SQLiteConnection connection;
    protected DAOEntity        entity;
    protected Relationships    relationships;


    public DAOGeneric(Context context) {
        this.entity   = getNewEntity();
        connection    = SQLiteConnection.getInstance(context);
        relationships = new Relationships();
        setRelations();
    }


    @TargetApi(Build.VERSION_CODES.N)
    protected void setRelations() {
        for(java.lang.reflect.Field field : entity.getClass().getDeclaredFields()) {
            for(Annotation annotation: field.getAnnotations()) {
                if(annotation instanceof DataBaseInfo) {
                    DataBaseInfo dbInfo = (DataBaseInfo) annotation;

                    this.relationships.addRelation(dbInfo.key(),
                            dbInfo.sequential(),
                            dbInfo.columnName(),
                            StringUtils.toBeanFormat(dbInfo.columnName()),
                            dbInfo.dataType().getSqlite(),
                            dbInfo.dataType().getType());
                }
            }
        }
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public String getTableName() {
        Table table = entity.getClass().getAnnotation(Table.class);

        return table.name();
    }

    public String getTableNameComplete() {
        Table table = entity.getClass().getAnnotation(Table.class);
        if(table.schema().isEmpty()) {
            return table.name();
        }

        return table.schema().toUpperCase() + "." + table.name().toLowerCase();
    }



    protected void catchSQLException(SQLException exception) {

    }

    @Override
    public boolean insert(DAOEntity entity) {
        connection.beginTransaction();

        try {
            StringBuilder sql = new StringBuilder("INSERT INTO ");
            sql.append(getTableNameComplete() + "(")
               .append(StringUtils.join(",", getRelationships().getAllNonSequentialsColumnsNames()))
               .append(") VALUES (");

            boolean first = true;

            for(ModelToDataBaseRelation relation : getRelationships().getAllRelations()) {
                if(relation.isSequential()) {
                    continue;
                }

                if(!first) {
                    sql.append(", ");
                }
                sql.append("'" + BeanUtils.callGetter(entity, relation.getModelName()) + "'");
                first = false;
            }

            sql.append(");");
//            sql.append(") RETURNING ").append(StringUtils.join(", ", getRelationships().getAllColumnsNames()));

            connection.getConnection().execSQL(sql.toString());
            connection.commit();

            for(ModelToDataBaseRelation relation : getRelationships().getAllRelations()) {
                if (relation.isSequential()) {
                    String sqlMax = String.format("select max(%1$s) %1$s from %2$s", relation.getColumnName(), getTableNameComplete());
                    Cursor cursor = getCursorFromSql(sqlMax);

                    if (!cursor.isAfterLast()) {
                        BeanUtils.callSetter(entity, relation.getModelName(), getValueFromCursor(cursor, relation));
                    }
                }
            }

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        } finally {
            connection.endTransaction();
        }
        return false;
    }

    @Override
    public boolean delete(DAOEntity entity) {
        connection.beginTransaction();

        try {
            StringBuilder query = new StringBuilder("DELETE FROM ");
            query.append(this.getTableNameComplete())
                 .append(" WHERE TRUE ");

            for(int i = 0; i < relationships.getAllKeyColumnsNames().size(); i++) {
                query.append("AND ")
                     .append(relationships.getAllKeyColumnsNames().get(i))
                     .append(" = ")
                     .append(BeanUtils.callGetter(entity, relationships.getAllKeyColumnsNames().get(i)));
            }

            connection.getConnection().execSQL(query.toString());
            connection.commit();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            connection.endTransaction();
        }
    }

    @Override
    public boolean update(DAOEntity entity) {
        connection.beginTransaction();

        try {
            StringBuilder query = new StringBuilder("UPDATE ");
            query.append(this.getTableNameComplete())
                 .append(" SET ");

            boolean firsField = true;
            for(int i = 0; i < getRelationships().getAllNonKeyColumnsNames().size(); i++) {
                if(firsField) {
                    firsField = false;
                } else {
                    query.append(", ");
                }
                query.append(getRelationships().getAllNonKeyColumnsNames().get(i))
                     .append(" = '")
                     .append(BeanUtils.callGetter(entity, getRelationships().getAllNonKeyModelNames().get(i)))
                     .append("' ");
            }

            query.append(" ")
                 .append("WHERE 1 = 1");

            for(int i = 0; i < relationships.getAllKeyColumnsNames().size(); i++) {
                query.append(" AND ")
                     .append(relationships.getAllKeyColumnsNames().get(i))
                     .append(" = ")
                     .append("'")
                     .append(BeanUtils.callGetter(entity, relationships.getAllKeyModelNames().get(i)))
                     .append("'");
            }

            connection.getConnection().execSQL(query.toString());
            connection.commit();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            connection.endTransaction();
        }
    }

    @Override
    public Iterable<DAOEntity> getAll() {
        StringBuilder query = new StringBuilder(getSqlGetAll());
        query.append(" WHERE 1=1");

        Cursor cursor = connection.getConnection().rawQuery(query.toString(), new String[0]);
        cursor.moveToFirst();

        List<DAOEntity> entities = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            DAOEntity entity = getNewEntity();
            beanModel(cursor, entity);
            entities.add(entity);
            cursor.moveToNext();
        }

        return entities;
    }

    @Override
    public boolean persists(DAOEntity entity) {
        StringBuilder query = new StringBuilder(getSqlGetAll());
        query.append(" WHERE 1 = 1");

        for(int i = 0; i < relationships.getAllKeyColumnsNames().size(); i++) {
            query.append(" AND ")
                    .append(relationships.getAllKeyColumnsNames().get(i))
                    .append(" = ")
//                    .append(BeanUtils.callGetter(entity, relationships.getAllKeyColumnsNames().get(i)));
                    .append(BeanUtils.callGetter(entity, relationships.getAllKeyModelNames().get(i)));
        }

        Cursor cursor = getCursorFromSql(query.toString());

        if (!cursor.isAfterLast()) {
            beanModel(cursor, entity);
        }

        return true;
    }

    protected Cursor getCursorFromSql(String sql) {
        Cursor cursor = connection.getConnection().rawQuery(sql, new String[0]);
        cursor.moveToFirst();
        return cursor;
    }

    protected void beanModel(Cursor cursor, DAOEntity entity) {
        for(ModelToDataBaseRelation relation : getRelationships().getAllRelations()) {
            try {
                BeanUtils.callSetter(entity, relation.getModelName(), getValueFromCursor(cursor, relation));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean exists(DAOEntity entity) {
        return false;
    }

    @Override
    public DAOEntity findOne(DAOEntity entity) {
        return null;
    }

    @Override
    public Iterator<DAOEntity> findAll(DAOEntity entity) {
        return null;
    }

    protected String getSqlGetAll() {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(StringUtils.join(", ", getRelationships().getAllColumnsNames()))
                .append(" FROM ")
                .append(this.getTableNameComplete());

        return query.toString();
    }

    @Override
    public DAOEntity getNewEntity() {
        String className  = this.getClass().getName();
        className         = className.substring(DAO_PATH.length() + PREFIX.length());
        try {
            Class claz = Class.forName(ENTITY_PATH + className);
            return udesc.br.rakesfoot.core.util.ClassUtils.getNewPOJO(claz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onCreate() {
//        connection.getConnection().execSQL("DROP TABLE IF EXISTS " + this.getTableNameComplete() + ";");
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(this.getTableNameComplete())
           .append(" (");

        List<String> columns = new ArrayList<>();
        for(ModelToDataBaseRelation relation : relationships.getAllRelations()) {
            columns.add(relation.getCreateExpression());
        }

        sql.append(StringUtils.join(", ", columns));
        sql.append(getScriptPk());

        String scriptFks = this.getScriptFks();
        if(scriptFks != null && !scriptFks.isEmpty()) {
            sql.append(", ");
            sql.append(scriptFks);
        }

        sql.append(");");

        try {
            connection.getConnection().execSQL(sql.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    private String getScriptPk() {
        StringBuilder sql = new StringBuilder();
        if (relationships.getAllKeyColumnsNames().size() > 1) {
            sql.append(", PRIMARY KEY (")
               .append(StringUtils.join(",", relationships.getAllKeyColumnsNames()))
               .append(")");
        }

        return sql.toString();
    }

    private String getScriptFks() {
        return null;
    }

    public void onUpgrade() {

    }

    public Object getValueFromCursor(Cursor cursor, ModelToDataBaseRelation relation) throws SQLException {
     int columnIndex = cursor.getColumnIndex(relation.getColumnName());
        switch(relation.getType().getName()) {
            case "java.lang.Double":
            case "double":
                return cursor.getDouble(columnIndex);

            case "java.lang.Float":
            case "float":
                return cursor.getFloat(columnIndex);

            case "java.lang.Long":
            case "long":
                return cursor.getLong(columnIndex);

            case "java.lang.Integer":
            case "int":
                return cursor.getInt(columnIndex);

            case "java.lang.String":
                return cursor.getString(columnIndex);

            case "java.util.Calendar":
            case "java.util.Date":
                return cursor.getString(columnIndex);

            default:
                throw new RuntimeException("Type not recognized: " + relation.getType().getName());
        }
    }

}