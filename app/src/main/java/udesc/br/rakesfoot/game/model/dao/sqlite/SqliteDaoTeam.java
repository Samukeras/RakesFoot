package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoTeam extends DAOGeneric<Team> {

    public SqliteDaoTeam(Context context, int version) {
        super(context, version);
    }

}
