package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoSeason;

/**
 * Created by felic on 01/11/2016.
 */
public class SeederGame implements Seeder {

    public SeederGame(Connection connection) {
        seed(connection);
    }

    @Override
    public void seed(Connection connection) {
        Persistible season = new SqliteDaoSeason(connection.getContext(), Connection.INITIAL_VERSION),
                    player = new SqliteDaoPlayer(connection.getContext(), Connection.INITIAL_VERSION);

        season.onCreate();
        player.onCreate();
    }
}
