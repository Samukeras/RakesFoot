package udesc.br.rakesfoot;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;

import udesc.br.rakesfoot.core.util.RoundRobinUtils;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoFactory;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoMatch;
import udesc.br.rakesfoot.game.simulator.ChampionshipSimulator;
import udesc.br.rakesfoot.game.simulator.Simulator;
import udesc.br.rakesfoot.game.simulator.SimulatorEventListener;

import static udesc.br.rakesfoot.game.model.EventType.GOAL;

public class PlayActivity extends GameActivity implements SimulatorEventListener {

    private ListView events;

    private TextView hostName;
    private TextView hostGoals;

    private TextView guestName;
    private TextView guestGoals;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_play);
    }

    @Override
    protected void startComponents() {
        setTitle(Game.getTeam().getName());

        events     = (ListView) findViewById(R.id.listEvents);
        hostName   = (TextView) findViewById(R.id.textHost);
        hostGoals  = (TextView) findViewById(R.id.textHostGoals);
        guestName  = (TextView) findViewById(R.id.textGuest);
        guestGoals = (TextView) findViewById(R.id.textGuestGoals);

        SqliteDaoMatch dao = (SqliteDaoMatch) SqliteDaoFactory.getDaoMatch(getBaseContext());
        Match match = dao.getMatch(Game.getTeam(), Game.getInstance().getCurrentSeason());

        hostName.setText(match.getHost().getName());
        guestName.setText(match.getGuest().getName());

        ArrayAdapter<String> eventListAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1);
        events.setAdapter(eventListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startSimulation();
    }

    private void startSimulation() {
        Simulator simulator = new ChampionshipSimulator(getBaseContext());
        simulator.addListener(this);
        simulator.register(Game.getInstance().getCurrentSeason().getChampionship(ChampionshipType.DIVISION_1));
        simulator.register(Game.getInstance().getCurrentSeason().getChampionship(ChampionshipType.DIVISION_2));


        Handler handler = new Handler();

        Thread thread = new Thread(simulator);
        handler.post(thread);
    }

    @Override
    public void onTrigger(Event event) {
        if (event.getMatch().getHostId() == Game.getTeam().getId() || event.getMatch().getGuestId() == Game.getTeam().getId()) {
            if (event.getType() == GOAL) {
                updateGoal(event);
            }
             updateList(event);
        }
    }

    private void updateGoal(Event event) {
        String goals;
        if (event.getTeam().getId() == event.getMatch().getHost().getId()) {
            goals = String.valueOf(event.getMatch().getEventCount(GOAL, event.getMatch().getHost()));
            hostGoals.setText(goals);
        } else {
            goals = String.valueOf(event.getMatch().getEventCount(GOAL, event.getMatch().getGuest()));
            guestGoals.setText(goals);
        }
    }

    private void updateList(Event event) {
        ((ArrayAdapter<String>) events.getAdapter()).add(event.getDescription());
    }
}
