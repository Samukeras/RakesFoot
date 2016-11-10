package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.simulator.ChampionshipSimulator;
import udesc.br.rakesfoot.game.simulator.Simulator;
import udesc.br.rakesfoot.game.simulator.SimulatorEventListener;

import static udesc.br.rakesfoot.game.model.EventType.GOAL;

public class PlayActivity extends AppCompatActivity implements SimulatorEventListener {

    private ListView events;

    private TextView hostName;
    private TextView hostGoals;

    private TextView guestName;
    private TextView guestGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setTitle(Game.getTeam().getName());

        events     = (ListView) findViewById(R.id.listEvents);
        hostName   = (TextView) findViewById(R.id.textHost);
        hostGoals  = (TextView) findViewById(R.id.textHostGoals);
        guestName  = (TextView) findViewById(R.id.textGuest);
        guestGoals = (TextView) findViewById(R.id.textGuestGoals);

        startSimulation();
    }

    private void startSimulation() {
        Simulator simulator = new ChampionshipSimulator(getBaseContext());
        simulator.addListener(this);
        simulator.register(Game.getInstance().getCurrentSeason().getChampionship(ChampionshipType.DIVISION_1));
        simulator.register(Game.getInstance().getCurrentSeason().getChampionship(ChampionshipType.DIVISION_2));
        simulator.run();
    }

    @Override
    public void onTrigger(Event event) {
        if (event.getTeam().getId() == Game.getTeam().getId()) {
            if (event.getType() == GOAL) {
                updateGoal(event);
            }
             updateList(event);
        }
    }

    private void updateGoal(Event event) {
        if (event.getTeam().getId() == event.getMatch().getHost().getId()) {
            hostGoals.setText(event.getMatch().getEventCount(GOAL, event.getMatch().getHost()));
        } else {
            guestGoals.setText(event.getMatch().getEventCount(GOAL, event.getMatch().getGuest()));
        }
    }

    private void updateList(Event event) {
        events.addHeaderView(new TextView(getBaseContext()), event.getDescription(), false);
    }
}
