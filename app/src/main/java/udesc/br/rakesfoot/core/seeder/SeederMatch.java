package udesc.br.rakesfoot.core.seeder;

import java.util.Map;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.RoundRobinUtils;
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
        int size = parent.getTeams().size();
        Match match;

        for (int round = 1; round < size; round++) {
            for (Map.Entry<Integer, Integer> map : RoundRobinUtils.getMatches(size, round).entrySet()) {
                match = new Match();
                match.setChampionship(parent);
                match.setRound(round);
                match.setHost(parent.getTeams().get(map.getKey()));
                match.setGuest(parent.getTeams().get(map.getValue()));

                getDao().insert(match);

                handle(match);
            }
        }
    }

    @Override
    public void crop(Championship parent) {

    }
}
