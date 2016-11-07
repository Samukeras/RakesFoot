package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Stadium;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoStadium extends DAOGeneric<Stadium> {

    public SqliteDaoStadium(Context context) {
        super(context);
    }

}
