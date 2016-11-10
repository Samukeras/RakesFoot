package udesc.br.rakesfoot.game.simulator;

import android.content.Context;

import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.Match;
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
    }

}
