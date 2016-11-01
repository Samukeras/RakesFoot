package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;

/**
 * Created by felic on 31/10/2016.
 */
public abstract class EntitySeeder implements Seeder {

    protected Connection connection;


    public EntitySeeder(Connection connection) {
        this.connection = connection;
        seed(connection);
    }

    @Override
    public void seed(Connection connection) {
        Persistible persistible = getDao();
        persistible.onCreate();
    }

    public abstract Persistible getDao();
}
