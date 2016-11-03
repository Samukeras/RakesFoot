package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoSeason;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederSeason extends EntitySeeder {

    public SeederSeason(Connection connection) {
        super(connection);
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoSeason(this.connection.getContext(), Connection.INITIAL_VERSION);
    }

    @Override
    public void seed(Connection connection) {
        Persistible persistible = getDao();
        persistible.onCreate();

        Season season = new Season();
        season.setYear(2016);

        boolean sucesso = persistible.insert(season);
    }

}
