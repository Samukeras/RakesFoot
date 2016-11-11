package udesc.br.rakesfoot;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import udesc.br.rakesfoot.game.model.Budget;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoBudget;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

import static udesc.br.rakesfoot.game.rules.Budget.STADIUM_IMPROVEMENT;

public class StadiumActivity extends GameActivity {

    private Button increaseCapacity;

    private TextView name,
                     capacity,
                     budget;

    private Stadium          stadium;
    private Budget           currentBudget;
    private SqliteDaoStadium dao;
    private SqliteDaoBudget  daoBudget;


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
        daoBudget     = new SqliteDaoBudget(getApplicationContext());
        currentBudget = daoBudget.getNewEntity();

        currentBudget.setTeam(Game.getTeam());
        daoBudget.persists(currentBudget);

        this.budget.setText(String.valueOf(currentBudget.getCurrentCash()));
    }

    public void onClickIncreaseCapacity(View v) {
        if(!(currentBudget.getCurrentCash() >= STADIUM_IMPROVEMENT)) {
            Toast.makeText(getBaseContext(), "Orçamento insuficiente!", Toast.LENGTH_SHORT);
        } else {
            stadium.setCapacity(stadium.getCapacity() + 10000);
            dao.update(stadium);

            currentBudget.setCurrentCash(currentBudget.getCurrentCash() - STADIUM_IMPROVEMENT);
            daoBudget.update(currentBudget);

            startActivity(new Intent(getApplicationContext(), TeamActivity.class));
        }
    }

}