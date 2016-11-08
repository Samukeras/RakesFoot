package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Manager;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoManager;

/**
 * Created by Ricardo on 07/11/2016.
 */
public class SeederManager extends EntitySeeder<Manager, Game> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoManager(getConnection().getContext());
    }

    @Override
    public void seed(Game parent) {
        Manager manager = parent.getManager();
        Championship championship = parent.getCurrentSeason().getChampionship(ChampionshipType.DIVISION_2);
        int randomTeam  = IntRandomUtils.getNextIntFromZeroToInterval(championship.getTeams().size() - 1);
        manager.setTeam(championship.getTeams().get(randomTeam));

        getDao().insert(manager);

        handle(manager);
    }

    @Override
    public void crop(Game parent) {

    }
}
