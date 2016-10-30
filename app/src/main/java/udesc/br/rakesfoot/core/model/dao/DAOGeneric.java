package udesc.br.rakesfoot.core.model.dao;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;
import udesc.br.rakesfoot.core.util.BeanUtils;
import udesc.br.rakesfoot.core.util.StringUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Samuel Felício Adriano
 * @param <DAOEntity>
 */
public class DAOGeneric<DAOEntity extends udesc.br.rakesfoot.core.model.Entity> implements Persistible<DAOEntity> {


    protected SQLiteConnection connection;
    protected DAOEntity        entity;
    protected Relationships    relationships;


    public DAOGeneric(Context context, int version) {
        this.entity   = getNewEntity();
        connection    = SQLiteConnection.getInstance(context, version);
        relationships = new Relationships();
        setRelations();
    }


    @TargetApi(Build.VERSION_CODES.N)
    protected void setRelations() {
        for(java.lang.reflect.Field field : entity.getClass().getDeclaredFields()) {
            for(DataBaseInfo dbInfo : field.getDeclaredAnnotationsByType(DataBaseInfo.class)) {
                this.relationships.addRelation(dbInfo.key(),
                        dbInfo.sequential(),
                        dbInfo.columnName(),
                        StringUtils.toBeanFormat(dbInfo.columnName()),
                        dbInfo.dataType().getSqlite(),
                        dbInfo.dataType().getType());
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
            sql.append(getTableNameComplete())
               .append(StringUtils.join(",", getRelationships().getAllNonSequentialsColumnsNames()))
               .append(") VALUES (")
               .append(StringUtils.join(",", getRelationships().getAllNonSequentialsPreparedParameters()))
               .append(") RETURNING ")
               .append(StringUtils.join(", ", getRelationships().getAllColumnsNames()));

            connection.getConnection().execSQL(sql.toString());
            connection.endTransaction();

            return true;
        } catch(Exception exception) {
            return false;
        }
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
            connection.endTransaction();

            return true;
        } catch(Exception exception) {
            return false;
        }
    }

    @Override
    public boolean update(DAOEntity entity) {
        connection.beginTransaction();

        try {
            StringBuilder query = new StringBuilder("UPDATE ");
            query.append(this.getTableNameComplete())
                 .append(" SET ");

            for(int i = 0; i < getRelationships().getAllNonKeyColumnsNames().size(); i++) {
                query.append(getRelationships().getAllNonKeyColumnsNames().get(i))
                     .append(" = ")
                     .append(BeanUtils.callGetter(entity, getRelationships().getAllNonKeyModelNames().get(i)))
                     .append(" ");
            }

            query.append(" ")
                 .append("WHERE TRUE");

            for(int i = 0; i < relationships.getAllKeyColumnsNames().size(); i++) {
                query.append(" AND ")
                     .append(relationships.getAllKeyColumnsNames().get(i))
                     .append(" = ")
                     .append(BeanUtils.callGetter(entity, relationships.getAllKeyColumnsNames().get(i)));
            }

            connection.getConnection().execSQL(query.toString());
            connection.endTransaction();

            return true;
        } catch(Exception exception) {
            return false;
        }
    }

    @Override
    public Iterable<DAOEntity> getAll() {
        return null;
    }

    @Override
    public boolean persists(DAOEntity entity) {
        return false;
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

    @Override
    public DAOEntity getNewEntity() {
        return udesc.br.rakesfoot.core.util.ClassUtils.getNewPOJO(this.entity.getClass());
    }

    public void onCreate() {
        StringBuilder sql = new StringBuilder("CREATE TABLE ");
        sql.append(this.getTableNameComplete())
           .append(" (");

        List<String> columns = new ArrayList<>();
        for(ModelToDataBaseRelation relation : relationships.getAllRelations()) {
            columns.add(relation.getColumnName() + " " + relation.getDbType());
        }

        sql.append(StringUtils.join(", ", columns));

        connection.getConnection().execSQL(sql.toString());
        createPks();
        createFks();
    }

    private void createPks() {
        StringBuilder sql = new StringBuilder("ALTER TABLE ");
        sql.append(getTableNameComplete())
           .append("ADD CONSTRAINT PK_")
           .append(this.getTableName())
           .append(" PRIMARY KEY (")
           .append(StringUtils.join(",", relationships.getAllKeyColumnsNames()))
           .append(")");

        connection.getConnection().execSQL(sql.toString());
    }

    private void createFks() {

    }

    public void onUpgrade() {

    }

}