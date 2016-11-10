package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;

/**
 * Created by Ricardo on 06/11/2016.
 */

public class SqliteDaoBudgetEntry extends DAOGeneric<udesc.br.rakesfoot.game.model.BudgetEntry> {

    public SqliteDaoBudgetEntry(Context context) {
        super(context);
    }
}
