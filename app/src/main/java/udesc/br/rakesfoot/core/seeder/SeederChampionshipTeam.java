package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipTeam;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionshipTeam;

/**
 * Created by Ricardo on 09/11/2016.
 */

public class SeederChampionshipTeam extends EntitySeeder<ChampionshipTeam, Championship> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoChampionshipTeam(getConnection().getContext());
    }

    @Override
    public void seed(Championship parent) {
        for (Team team : parent.getTeams()) {
            getDao().insert(new ChampionshipTeam(team, parent));
        }
    }

    @Override
    public void crop(Championship parent) {}
}
