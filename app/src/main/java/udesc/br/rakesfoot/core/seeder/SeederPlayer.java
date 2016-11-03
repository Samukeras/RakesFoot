package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.NameUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;

/**
 * Created by felic on 31/10/2016.
 */
public class SeederPlayer extends EntitySeeder {

    private static int NUMBER = 20;


    public SeederPlayer(Connection connection) {
        super(connection);
        insertPlayers(0);
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoPlayer(connection.getContext(), Connection.INITIAL_VERSION);
    }

    public void insertPlayers(int number) {
        if(number >= NUMBER) {
            return;
        }

        Player player = new Player();

        String name = NameUtils.generateRandomFirstName(connection.getContext());
    }

    @Override
    public void seed(Connection connection) {
        connection.beginTransaction();
        Persistible persistible = getDao();
        persistible.onCreate();
    }

}