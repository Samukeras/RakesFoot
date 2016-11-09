package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Manager;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

public class StadiumActivity extends AppCompatActivity {

    private Button increaseCapacity;

    private TextView name,
                     capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);

        onCreate();
    }

    private void onCreate() {
        startComponents();
        fillFields();
    }

    private void startComponents() {
        increaseCapacity = (Button)   findViewById(R.id.buttonIncrease);
        name             = (TextView)findViewById(R.id.textName);
        capacity         = (TextView)findViewById(R.id.textCapacity);
    }

    private void fillFields() {
        DAOGeneric dao = new SqliteDaoTeam(getBaseContext());
        dao.persists(Game.getInstance().getManager().getTeam());
        Stadium stadium = Game.getInstance().getManager().getTeam().getStadium();
        int i = 1;
    }

    public void onClickIncreaseCapacity(View v) {

    }

}
