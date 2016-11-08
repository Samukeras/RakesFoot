package udesc.br.rakesfoot.core.seeder;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;

/**
 * Created by felic on 31/10/2016.
 */
public abstract class EntitySeeder<Seed, Parent> implements Seeder<Parent> {

    private Connection connection;

    private List<EntitySeeder> chain = new ArrayList<>();

    public boolean addSucessor(EntitySeeder seeder) {
        seeder.setConnection(connection);
        return chain.add(seeder);
    }

    public abstract Persistible getDao();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected final void handle(Seed parent) {
        for (EntitySeeder seeder : chain) {
            handleMethod(seeder, (Parent) parent);
        }
    }

    @Override
    public void beforeSeed() {
        if (getDao() != null) {
            getDao().onCreate();
        }
    }

    protected void handleMethod(Seeder seeder, Parent parent) {
        if (connection.getVersion() == Connection.INITIAL_VERSION) {
            seeder.beforeSeed();
            seeder.seed(parent);
        } else {
            seeder.crop(parent);
        }
    }

}
