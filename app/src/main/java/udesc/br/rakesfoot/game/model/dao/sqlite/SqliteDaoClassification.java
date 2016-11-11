package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.TeamClassification;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

public class SqliteDaoClassification extends DAOGeneric<TeamClassification> {

    public SqliteDaoClassification(Context context) {
        super(context);
    }

    protected void setRelations() {
        this.relationships.addRelation(false, false, "team_name"    , "team.name"   , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "position"     , "position"    , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "victories"    , "victories"   , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "losses"       , "losses"      , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "draws"        , "draws"       , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "goals_pro"    , "goalsPro"    , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "goals_against", "goalsAgainst", INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "goals_balance", "goalsBalance", INT_INTEGER.getSqlite(), INT_INTEGER.getType());
        this.relationships.addRelation(false, false, "points"       , "points"      , INT_INTEGER.getSqlite(), INT_INTEGER.getType());
    }

    @Override
    protected String getSqlGetAll() {
        String sql = "\n" +
                "  SELECT *\n" +
                "    FROM (\n" +
                "          SELECT *,\n" +
                "                 victories * 3 + draws      AS points,\n" +
                "                 goals_pro - goals_against AS goals_balance\n" +
                "            FROM (\n" +
                "                  SELECT team.name AS team_name,\n" +
                "                         COUNT((SELECT 1\n" +
                "                                  FROM match\n" +
                "                                 WHERE (host_id  = team.id\n" +
                "                                        AND\n" +
                "                                        winner   = 2)\n" +
                "                                    OR (guest_id = team.id\n" +
                "                                        AND\n" +
                "                                        winner   = 3))) AS victories,\n" +
                "                         COUNT((SELECT 1\n" +
                "                                  FROM match\n" +
                "                                 WHERE winner = 1\n" +
                "                                   AND (team.id  = guest_id\n" +
                "                                        OR\n" +
                "                                        winner   = 3))) AS draws,\n" +
                "                         COUNT((SELECT 1\n" +
                "                                  FROM match\n" +
                "                                 WHERE (team.id  = host_id\n" +
                "                                        AND\n" +
                "                                        winner   = 3)\n" +
                "                                    OR (team.id  = guest_id\n" +
                "                                        AND\n" +
                "                                        winner   = 2))) AS losses,\n" +
                "                         COUNT((SELECT 1\n" +
                "                                  FROM event\n" +
                "                                 WHERE type = 1\n" +
                "                                   AND event.team_id = team.id)) AS goals_pro,\n" +
                "                         COUNT((SELECT 1\n" +
                "                                  FROM event\n" +
                "                                  JOIN match\n" +
                "                                    ON match.id = event.match_id\n" +
                "                                   AND (match.host_id  = team.id\n" +
                "                                        OR\n" +
                "                                        match.guest_id = team.id)\n" +
                "                                   AND winner <> 0\n" +
                "                                 WHERE type = 1\n" +
                "                                   AND event.team_id <> team.id)) AS goals_against\n" +
                "                    FROM team) data) classification\n" +
                "ORDER BY points,\n" +
                "         goals_balance,\n" +
                "         goals_pro,\n" +
                "         victories";

        return sql;
    }

    @Override
    public Iterable<TeamClassification> getAll() {
        try {

            Cursor cursor = connection.getConnection().rawQuery(getSqlGetAll(), new String[0]);
            cursor.moveToFirst();

            List<TeamClassification> entities = new ArrayList<>();

            while(!cursor.isAfterLast()) {
                TeamClassification entity = getNewEntity();
                beanModel(cursor, entity);
                entities.add(entity);
                cursor.moveToNext();
            }

            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            int i = 0;
            int b = i;

            return null;
        }
    }

    @Override
    public TeamClassification getNewEntity() {
        return new TeamClassification();
    }

}