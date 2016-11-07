package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
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
        EntitySeeder season       = new SeederSeason();
        EntitySeeder championship = new SeederChampionship();
        EntitySeeder team         = new SeederTeam();
        EntitySeeder stadium      = new SeederStadium();
        EntitySeeder player       = new SeederPlayer();
        EntitySeeder match        = new SeederMatch();

        this.addSucessor(season);
        season.addSucessor(championship);
        championship.addSucessor(team);
        championship.addSucessor(match);
        team.addSucessor(player);
        team.addSucessor(stadium);

        handleMethod(this, null);
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
