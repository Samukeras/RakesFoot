package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Manager;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoManager extends DAOGeneric<Manager> {

    public SqliteDaoManager(Context context) {
        super(context);
    }

}
