package udesc.br.rakesfoot.core.seeder;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoEvent;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoManager;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoMatch;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoSeason;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

/**
 * Created by felic on 01/11/2016.
 */
public class SeederGame implements Seeder {

    public SeederGame(Connection connection) {
        seed(connection);
    }

    @Override
    public void seed(Connection connection) {
        SeederSeason       ss  = new SeederSeason(connection);
        SeederChampionship sc  = new SeederChampionship(connection, ss.getSeason());
        SeederStadium      sst = new SeederStadium(connection);
        SeederTeam         st  = new SeederTeam(connection);
//        List<Persistible> seeds = new ArrayList<>();
//        seeds.add(new SqliteDaoSeason(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoChampionship(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoEvent(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoManager(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoMatch(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoPlayer(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoStadium(connection.getContext(), Connection.INITIAL_VERSION));
//        seeds.add(new SqliteDaoTeam(connection.getContext(), Connection.INITIAL_VERSION));
//
//        for(Persistible seed : seeds) {
//            seed.onCreate();
//        }
    }
}
