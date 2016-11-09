package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

public class StadiumActivity extends AppCompatActivity {

    private Button increaseCapacity;

    private TextView name,
                     capacity;

    Stadium stadium;
    SqliteDaoStadium dao;

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
        name             = (TextView) findViewById(R.id.textName);
        capacity         = (TextView) findViewById(R.id.textCapacity);

        dao              = new SqliteDaoStadium(getBaseContext());
    }

    private void fillFields() {
        SqliteDaoTeam daoTeam = new SqliteDaoTeam(getBaseContext());
        daoTeam.persists(Game.getInstance().getManager().getTeam());
        stadium = dao.getStadiumFromTeam(Game.getInstance().getManager().getTeam());

        name.setText(stadium.getName());
        capacity.setText(String.valueOf(stadium.getMaxCapacity()));
    }

    public void onClickIncreaseCapacity(View v) {
        stadium.setMaxCapacity(stadium.getMaxCapacity() + 10000);
        dao.update(stadium);
    }

}
