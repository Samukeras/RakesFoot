package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Budget;

/**
 * Created by Ricardo on 06/11/2016.
 */

public class SqliteDaoBudget extends DAOGeneric<Budget> {

    public SqliteDaoBudget(Context context) {
        super(context);
    }
}
