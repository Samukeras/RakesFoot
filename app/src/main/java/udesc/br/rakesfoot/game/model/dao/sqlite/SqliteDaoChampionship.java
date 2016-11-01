package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Championship;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoChampionship extends DAOGeneric<Championship> {

    public SqliteDaoChampionship(Context context, int version) {
        super(context, version);
    }

}
