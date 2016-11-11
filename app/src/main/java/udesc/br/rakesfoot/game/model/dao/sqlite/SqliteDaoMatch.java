package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoMatch extends DAOGeneric<Match> {

    public SqliteDaoMatch(Context context) {
        super(context);
    }

    public List<Match> getAllMatches(Championship championship) {
        List<Match> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(getSqlGetAll());
        sql.append(" WHERE ")
                .append(" championship_id = ")
                .append(championship.getId())
                .append(" AND ")
                .append(" round = ")
                .append(championship.getRound());

        Cursor cursor = getCursorFromSql(sql.toString());

        while (!cursor.isAfterLast()) {
            Match entity = getNewEntity();
            beanModel(cursor, entity);

            entity.setChampionship(championship);
            entity.setHost(championship.getTeam(entity.getHostId()));
            entity.setGuest(championship.getTeam(entity.getGuestId()));

            list.add(entity);
            cursor.moveToNext();
        }

        return list;
    }

    public Match getMatch(Team team, Season season) {
        StringBuilder sql = new StringBuilder(getSqlGetAll());
        sql.append(" WHERE ")
                .append(" championship_id in (select sub.id from championship sub where season = ")
                .append(season.getYear())
                .append(")")
                .append(" and ")
                .append(team.getId())
                .append(" in (host_id, guest_id)")
                .append(" and ")
                .append(" round = ")
                .append(season.getChampionship(ChampionshipType.DIVISION_1).getRound());

        Cursor cursor = getCursorFromSql(sql.toString());
        Match  entity = getNewEntity();

        if (!cursor.isAfterLast()) {
            beanModel(cursor, entity);

            for (Championship champ : season.getChampionships()) {
                if (entity.getChampionshipId() == champ.getId()) {
                    entity.setChampionship(champ);
                    entity.setHost(champ.getTeam(entity.getHostId()));
                    entity.setGuest(champ.getTeam(entity.getGuestId()));
                }
            }
        }

        return entity;
    }
}
