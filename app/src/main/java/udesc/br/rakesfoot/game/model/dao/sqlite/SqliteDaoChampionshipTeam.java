package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipTeam;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by Ricardo on 09/11/2016.
 */

public class SqliteDaoChampionshipTeam extends DAOGeneric<ChampionshipTeam> {

    public SqliteDaoChampionshipTeam(Context context) {
        super(context);
    }

    public List<Team> getAllTeams(Championship championship) {
        List<Team> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(getSqlGetAll());
        sql.append(" WHERE ")
                .append(" championship_id = ")
                .append(championship.getId());

        Cursor cursor = getCursorFromSql(sql.toString());

        while (!cursor.isAfterLast()) {
            ChampionshipTeam entity = getNewEntity();
            beanModel(cursor, entity);
            list.add(entity.getTeam());
            cursor.moveToNext();
        }

        return list;
    }

}
