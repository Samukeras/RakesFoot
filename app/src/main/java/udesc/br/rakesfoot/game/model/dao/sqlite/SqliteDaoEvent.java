package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Event;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoEvent extends DAOGeneric<Event> {

    public SqliteDaoEvent(Context context) {
        super(context);
    }

    protected void setRelations() {
        this.relationships.addRelation(false, false, "team_name"    , "team.name"   , STRING_VARCHAR.getSqlite(), STRING_VARCHAR.getType());
        this.relationships.addRelation(false, false, "position"     , "position"    , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "victories"    , "victories"   , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "losses"       , "losses"      , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "draws"        , "draws"       , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "goals_pro"    , "goalsPro"    , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "goals_against", "goalsAgainst", INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "goals_balance", "goalsBalance", INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
        this.relationships.addRelation(false, false, "points"       , "points"      , INT_INTEGER.getSqlite()   , INT_INTEGER.getType()   );
    }

}