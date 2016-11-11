package udesc.br.rakesfoot.game.simulator;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoFactory;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoMatch;

/**
 * Created by Ricardo on 10/11/2016.
 */

public class ChampionshipSimulator extends Simulator<Championship> {

    private final Context context;

    public ChampionshipSimulator(Context context) {
        this.context = context;
    }

    MatchSimulator matchSimulator = new MatchSimulator();

    @Override
    public boolean addListener(SimulatorListener listener) {
        matchSimulator.addListener(listener);
        return super.addListener(listener);
    }

    @Override
    void simulate() {
        SqliteDaoMatch daoMatch = new SqliteDaoMatch(context);

        for (Match match : daoMatch.getAllMatches(getCurrent())) {
            matchSimulator.register(match);
        }

        matchSimulator.run();
        update();
    }

    private void update() {
        DAOGeneric dao = SqliteDaoFactory.getDaoChampionship(context);
        getCurrent().setRound(getCurrent().getRound() + 1);
        dao.update(getCurrent());
    }

    private void updateMatches() {
        DAOGeneric dao = SqliteDaoFactory.getDaoMatch(context);

        for (Match match : matchSimulator.getTransients()) {
            dao.update(match);

            updateTeam(match.getHost());
            updateTeam(match.getGuest());

            for (Event event: match.getEvents()) {
                updateEvent(event);
            }
        }
    }

    @Override
    public void run() {
        super.run();
        updateMatches();
    }

    private void updateEvent(Event event) {
        DAOGeneric dao = SqliteDaoFactory.getDaoEvent(context);
        dao.insert(event);
    }

    private void updateTeam(Team team) {
        DAOGeneric dao = SqliteDaoFactory.getDaoTeam(context);
        dao.update(team);

        for (Player player : team.getPlayers()) {
            updatePlayer(player);
        }
    }

    private void updatePlayer(Player player) {
        DAOGeneric dao = SqliteDaoFactory.getDaoPlayer(context);
        dao.update(player);
    }

}
