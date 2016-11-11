package udesc.br.rakesfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udesc.br.rakesfoot.game.model.Budget;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoBudget;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

public class StadiumActivity extends GameActivity {

    private Button increaseCapacity;

    private TextView name,
                     capacity,
                     budget;

    private Stadium          stadium;
    private SqliteDaoStadium dao;


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_stadium);
    }

    protected void startComponents() {
        increaseCapacity = (Button)   findViewById(R.id.buttonIncrease);
        name             = (TextView) findViewById(R.id.textName);
        capacity         = (TextView) findViewById(R.id.textCapacity);
        budget           = (TextView) findViewById(R.id.textBudgetStadium);

        dao              = new SqliteDaoStadium(getBaseContext());
        fillFields();
    }

    private void fillFields() {
        SqliteDaoTeam daoTeam = new SqliteDaoTeam(getBaseContext());
        daoTeam.persists(Game.getInstance().getManager().getTeam());
        stadium = dao.getStadiumFromTeam(Game.getInstance().getManager().getTeam());

        name.setText(stadium.getName());
        capacity.setText(String.valueOf(stadium.getCapacity()));

        fillFieldsBudget();
    }

    private void fillFieldsBudget() {
        SqliteDaoBudget daoBudget = new SqliteDaoBudget(getApplicationContext());
        Budget          budget    = daoBudget.getNewEntity();

        budget.setTeam(Game.getTeam());
        daoBudget.persists(budget);

        this.budget.setText(String.valueOf(budget.getCurrentCash()));
    }

    public void onClickIncreaseCapacity(View v) {
        stadium.setCapacity(stadium.getCapacity() + 10000);
        dao.update(stadium);

        startActivity(new Intent(getApplicationContext(), TeamActivity.class));
    }

}