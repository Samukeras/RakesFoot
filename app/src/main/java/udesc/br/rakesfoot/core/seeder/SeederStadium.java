package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederStadium extends EntitySeeder<Stadium, Team> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoStadium(getConnection().getContext());
    }

    @Override
    public void seed(Team parent) {
        Stadium stadium = new Stadium();
        stadium.setName("Estádio do " + parent.getName());
        stadium.setId(parent.getId());
        stadium.setMaxCapacity(20000);

        getDao().insert(stadium);

        parent.setStadium(stadium);
        handle(stadium);
    }

    @Override
    public void crop(Team parent) {

    }
}
