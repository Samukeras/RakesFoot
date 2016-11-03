package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederChampionship extends EntitySeeder {

    Season season;

    public SeederChampionship(Connection connection, Season season) {
        super(connection);
        this.season = season;
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoChampionship(this.connection.getContext(), Connection.INITIAL_VERSION);
    }

    @Override
    public void seed(Connection connection) {
        Persistible persistible = getDao();
        persistible.onCreate();

        Championship championship = new Championship();
        championship.setName("Brasileirão Série A");
        championship.setSeason(season);

        persistible.insert(championship);
    }

}
