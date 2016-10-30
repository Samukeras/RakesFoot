package udesc.br.rakesfoot.core.model.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel Felício Adriano
 */
public class Relationships<Entity extends udesc.br.rakesfoot.core.model.Entity> {

    private List<ModelToDataBaseRelation> relations = new java.util.ArrayList<>();


    public void addRelation(boolean key, boolean sequential, String columnName, String modelName, String dbType, Class type) {
        relations.add(new ModelToDataBaseRelation(key, sequential, columnName, modelName, dbType, type));
    }

    public List<ModelToDataBaseRelation> getAllRelations() {
        return relations;
    }

    public List<String> getAllColumnsNames() {
        List<String> columns = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            columns.add(relation.getColumnName());
        }

        return columns;
    }

    public List<String> getAllNonSequentialsColumnsNames() {
        List<String> columns = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(!relation.isSequential()) {
                columns.add(relation.getColumnName());
            }
        }

        return columns;
    }

    public List<String> getAllKeyColumnsNames() {
        List<String> columns = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(relation.isKey()) {
                columns.add(relation.getColumnName());
            }
        }

        return columns;
    }

    public List<String> getAllPreparedParameters() {
        List<String> parameters = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            parameters.add("?");
        }

        return parameters;
    }

    public List<String> getAllNonSequentialsPreparedParameters() {
        List<String> parameters = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(!relation.isSequential()) {
                parameters.add("?");
            }
        }

        return parameters;
    }

    public List<Class> getAllTypes() {
        List<Class> types = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            types.add(relation.getType());
        }

        return types;
    }

    public List<Class> getAllNonSequentialsTypes() {
        List<Class> types = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(!relation.isSequential()) {
                types.add(relation.getType());
            }
        }

        return types;
    }

    public List<Class> getAllKeyTypes() {
        List<Class> types = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(relation.isKey()) {
                types.add(relation.getType());
            }
        }

        return types;
    }

    public List<String> getAllModelNames() {
        List<String> types = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            types.add(relation.getModelName());
        }

        return types;
    }

    public List<String> getAllNonSequentialsModelNames() {
        List<String> types = new ArrayList<>();

        for(ModelToDataBaseRelation relation : relations) {
            if(!relation.isSequential()) {
                types.add(relation.getModelName());
            }
        }

        return types;
    }

}