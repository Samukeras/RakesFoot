package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoEvent;

public class SeederEvent extends EntitySeeder<Event, Game> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoEvent(getConnection().getContext());
    }

    @Override
    public void seed(Game parent) {

    }

    @Override
    public void crop(Game parent) {

    }

}