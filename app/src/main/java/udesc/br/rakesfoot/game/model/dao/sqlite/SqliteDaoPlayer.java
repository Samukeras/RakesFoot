package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoPlayer extends DAOGeneric<Player> {

    public SqliteDaoPlayer(Context context) {
        super(context);
    }

    public List<Player> getAllPlayers(Team team) {
        List<Player> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(getSqlGetAll());
        sql.append(" WHERE ")
                .append(" team_id = ")
                .append(team.getId())
                .append(" order by position, overral");

        Cursor cursor = getCursorFromSql(sql.toString());

        while (!cursor.isAfterLast()) {
            Player entity = getNewEntity();
            beanModel(cursor, entity);
            list.add(entity);
            cursor.moveToNext();
        }

        return list;
    }

}
