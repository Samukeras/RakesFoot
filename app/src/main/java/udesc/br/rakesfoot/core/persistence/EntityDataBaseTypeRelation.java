package udesc.br.rakesfoot.core.persistence;

import java.util.Date;

/**
 * Provides the relations between the fields classes of the entity and the
 * Data Types of the DataBase
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  08/05/2016
 * @version 2.0
 */
public enum EntityDataBaseTypeRelation {

     DOUBLE_NUMERIC(double.class, "numeric")
    ,INT_INTEGER   (int.class   , "integer")
    ,DATE_DATE     (Date.class  , "date")
    ,LONG_INT      (long.class  , "integer")
    ,LONG_BIGINT   (long.class  , "bigint")
    ,STRING_VARCHAR(String.class, "varchar");


    private Class  type;
    private String sqlite;


    private EntityDataBaseTypeRelation(Class type, String sqlite) {
        this.type       = type;
        this.sqlite = sqlite;
    }

    public Class getType() {
        return type;
    }

    public String getSqlite() {
        return sqlite;
    }

}