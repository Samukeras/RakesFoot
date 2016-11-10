package udesc.br.rakesfoot.game.model.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by felic on 01/11/2016.
 */
public class SqliteDaoStadium extends DAOGeneric<Stadium> {

    public SqliteDaoStadium(Context context) {
        super(context);
    }

    public Stadium getStadiumFromTeam(Team team) {
        String sql = "SELECT stadium_id FROM team WHERE id = " + team.getId();
        Cursor cursor = getCursorFromSql(sql);

        int stadiumId = 0;
        if(!cursor.isAfterLast()) {
            stadiumId = cursor.getInt(cursor.getColumnIndex("stadium_id"));
        }

        Stadium stadium = new Stadium();
        stadium.setId(stadiumId);
        this.persists(stadium);

        return stadium;
    }

}