package udesc.br.rakesfoot.core.seeder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.game.model.Formation;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Position;
import udesc.br.rakesfoot.game.model.Team;

/**
 * Created by Ricardo on 10/11/2016.
 */

public class SeederFormation extends EntitySeeder<Formation, Team> {

    @Override
    public Persistible getDao() {
        return null;
    }

    @Override
    public void seed(Team parent) {
        buidFormation(parent);
    }

    @Override
    public void crop(Team parent) {
        buidFormation(parent);
    }

    private void buidFormation(Team team) {
        int limit;
        Formation formation = team.getFormation();

        Player[]    players = new Player[team.getPlayers().size()];
        players = team.getPlayers().toArray(players);

        Map<Position, Integer> firstTeamCount  = new HashMap<>();
        Map<Position, Integer> substituteCount = new HashMap<>();

        Arrays.sort(players);

        for (int i = 0; i < players.length; i++) {
            limit = formation.getFormationType().getFirstTeamQuantity(players[i].position());
            if (!firstTeamCount.containsKey(players[i].position())) {
                firstTeamCount.put(players[i].position(), 0);
            }
            if (firstTeamCount.get(players[i].position()) < limit) {
                firstTeamCount.put(players[i].position(), firstTeamCount.get(players[i].position()) + 1);
                formation.addFirstTeamPlayer(players[i]);
            } else {
                limit = formation.getFormationType().getSubstituteQuantity(players[i].position());
                if (!substituteCount.containsKey(players[i].position())) {
                    substituteCount.put(players[i].position(), 0);
                }
                if (substituteCount.get(players[i].position()) < limit) {
                    substituteCount.put(players[i].position(), substituteCount.get(players[i].position()) + 1);
                    formation.addSubstitute(players[i]);
                }
            }
        }

    }
}
