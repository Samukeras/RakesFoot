package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import java.lang.annotation.Annotation;
import java.sql.SQLException;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.core.model.dao.ModelToDataBaseRelation;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.util.BeanUtils;
import udesc.br.rakesfoot.core.util.StringUtils;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.DOUBLE_NUMERIC;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoTeam extends DAOGeneric<Team> {

    public SqliteDaoTeam(Context context) {
        super(context);
    }

    protected void setRelations() {
        this.relationships.addRelation(true , true , "id"             , "id"             , INT_INTEGER.getSqlite()   , INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "chemestry"      , "chemestry"      , DOUBLE_NUMERIC.getSqlite(), DOUBLE_NUMERIC.getType());
        this.relationships.addRelation(false, false, "motivation"     , "motivation"     , DOUBLE_NUMERIC.getSqlite(), DOUBLE_NUMERIC.getType());
        this.relationships.addRelation(false, false, "name"           , "name"           , STRING_VARCHAR.getSqlite(), STRING_VARCHAR.getType());
        this.relationships.addRelation(false, false, "initials"       , "initials"       , STRING_VARCHAR.getSqlite(), STRING_VARCHAR.getType());
        this.relationships.addRelation(false, false, "mainColor"      , "mainColor"      , INT_INTEGER.getSqlite()   , INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "secondaryColor" , "secondaryColor" , INT_INTEGER.getSqlite()   , INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "stadium_id"     , "Stadium.id"     , INT_INTEGER.getSqlite()   , INT_INTEGER.getType());
    }

}