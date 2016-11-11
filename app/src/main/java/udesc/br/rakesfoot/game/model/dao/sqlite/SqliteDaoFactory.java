package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.core.util.connection.Connection;

/**
 * Created by Ricardo on 10/11/2016.
 */

public class SqliteDaoFactory {

    private static Map<String, DAOGeneric> map = new HashMap<>();

    public static DAOGeneric getDaoChampionship(Context context) {
        if (!map.containsKey(SqliteDaoChampionship.class.getName())) {
            map.put(SqliteDaoChampionship.class.getName(), new SqliteDaoChampionship(context));
        }
        return map.get(SqliteDaoChampionship.class.getName());
    }

    public static DAOGeneric getDaoTeam(Context context) {
        if (!map.containsKey(SqliteDaoTeam.class.getName())) {
            map.put(SqliteDaoTeam.class.getName(), new SqliteDaoTeam(context));
        }
        return map.get(SqliteDaoTeam.class.getName());
    }

    public static DAOGeneric getDaoMatch(Context context) {
        if (!map.containsKey(SqliteDaoMatch.class.getName())) {
            map.put(SqliteDaoMatch.class.getName(), new SqliteDaoMatch(context));
        }
        return map.get(SqliteDaoMatch.class.getName());
    }

    public static DAOGeneric getDaoEvent(Context context) {
        if (!map.containsKey(SqliteDaoEvent.class.getName())) {
            map.put(SqliteDaoEvent.class.getName(), new SqliteDaoEvent(context));
        }
        return map.get(SqliteDaoEvent.class.getName());
    }

    public static DAOGeneric getDaoPlayer(Context context) {
        if (!map.containsKey(SqliteDaoPlayer.class.getName())) {
            map.put(SqliteDaoPlayer.class.getName(), new SqliteDaoPlayer(context));
        }
        return map.get(SqliteDaoPlayer.class.getName());
    }

}
