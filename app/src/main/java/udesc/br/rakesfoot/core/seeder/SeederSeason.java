package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoSeason;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederSeason extends EntitySeeder<Season, Game> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoSeason(getConnection().getContext());
    }

    @Override
    public void seed(Game parent) {
        Season season = new Season();
        season.setYear(2016);

        getDao().insert(season);
        parent.setCurrentSeason(season);

        handle(season);
    }

    @Override
    public void crop(Game parent) {
        for (Season season : (Iterable<Season>) getDao().getAll()) {
            parent.setCurrentSeason(season);
            handle(season);
        }
    }
}
