package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.support.annotation.Nullable;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.TeamClassification;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

public class SqlDaoClassification extends DAOGeneric<TeamClassification> {

    public SqlDaoClassification(Context context) {
        super(context);
    }

    protected void setRelations() {
        this.relationships.addRelation(false, false, "team_id"  , "team.id"  , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "position" , "position" , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "victories", "victories", INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "defeats"  , "defeats"  , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "draws"    , "draws"    , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "gp"       , "gp"       , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "ga"       , "ga"       , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "gb"       , "gb"       , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "points"   , "points"   , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
    }

    @Nullable
    public TeamClassification[] getClassification(int season) {
        return null;
    }

}
