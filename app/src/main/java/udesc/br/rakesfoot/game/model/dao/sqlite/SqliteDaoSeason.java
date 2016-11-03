package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.core.util.StringUtils;
import udesc.br.rakesfoot.game.model.Season;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoSeason extends DAOGeneric<Season> {

    public SqliteDaoSeason(Context context, int version) {
        super(context, version);
    }

}
