package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;

/**
 * Created by Ricardo on 06/11/2016.
 */

public class BudgetEntry extends DAOGeneric<udesc.br.rakesfoot.game.model.BudgetEntry> {

    public BudgetEntry(Context context, int version) {
        super(context, version);
    }
}
