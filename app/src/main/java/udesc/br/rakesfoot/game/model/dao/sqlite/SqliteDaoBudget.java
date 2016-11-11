package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Budget;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.DOUBLE_NUMERIC;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Created by Ricardo on 06/11/2016.
 */

public class SqliteDaoBudget extends DAOGeneric<Budget> {

    public SqliteDaoBudget(Context context) {
        super(context);
    }

    protected void setRelations() {
        this.relationships.addRelation(true , false, "team_id"     , "team.id"    , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "start_cash"  , "startCash"  , DOUBLE_NUMERIC.getSqlite(), DOUBLE_NUMERIC.getType());
        this.relationships.addRelation(false, false, "current_cash", "currentCash", DOUBLE_NUMERIC.getSqlite(), DOUBLE_NUMERIC.getType());
    }

}
