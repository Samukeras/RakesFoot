package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;

/**
 * Created by Ricardo on 03/11/2016.
 */

public class SeederMatch extends EntitySeeder<Match, Championship> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoChampionship(getConnection().getContext());
    }

    @Override
    public void seed(Championship parent) {

    }

    @Override
    public void crop(Championship parent) {

    }
}
