package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Budget;

/**
 * Created by Ricardo on 06/11/2016.
 */

public class SqliteBudget extends DAOGeneric<Budget> {

    public SqliteBudget(Context context, int version) {
        super(context, version);
    }
}
