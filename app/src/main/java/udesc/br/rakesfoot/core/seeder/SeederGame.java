package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Game;

/**
 * Created by felic on 01/11/2016.
 */
public class SeederGame extends EntitySeeder<Game, Object> {

    @Override
    public Persistible getDao() {
        return null;
    }

    public void start() {
        handle(Game.getInstance());
    }

    @Override
    public void seed(Object parent) {
        handle(Game.getInstance());
    }

    @Override
    public void crop(Object parent) {
        handle(Game.getInstance());
    }
}
