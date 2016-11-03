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
    Championship serieA,
                 serieB;


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

        serieA = new Championship();
        serieA.setName("Brasileirão Série A");
        serieA.setSeason(season);

        persistible.insert(serieA);

        serieB = new Championship();
        serieB.setName("Brasileirão Série B");
        serieB.setSeason(season);

        persistible.insert(serieB);
    }

    public Championship getSerieA() {
        return serieA;
    }

    public Championship getSerieB() {
        return serieB;
    }

}