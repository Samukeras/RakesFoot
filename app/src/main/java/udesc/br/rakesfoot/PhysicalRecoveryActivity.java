package udesc.br.rakesfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import udesc.br.rakesfoot.game.model.Budget;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoBudget;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

import static udesc.br.rakesfoot.game.rules.Player.PHYSICAL_RECOVERY_COST;

public class PhysicalRecoveryActivity extends GameActivity {

    private TextView budget;
    private Budget currentBudget;
    private SqliteDaoBudget daoBudget;


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_physical_recovery);
    }

    @Override
    protected void startComponents() {
        budget = (TextView) findViewById(R.id.textOrcamentoRecJog);

        fillBudget();
    }

    private void fillBudget() {
        daoBudget     = new SqliteDaoBudget(getApplicationContext());
        currentBudget = daoBudget.getNewEntity();

        currentBudget.setTeam(Game.getTeam());
        daoBudget.persists(currentBudget);

        this.budget.setText(String.valueOf(currentBudget.getCurrentCash()));
    }

    public void onClickRecoverAll(View view) {
        SqliteDaoPlayer dao = new SqliteDaoPlayer(getApplicationContext());
        udesc.br.rakesfoot.game.rules.Player rule = new udesc.br.rakesfoot.game.rules.Player();

        for(Player player : dao.getAllPlayers(Game.getInstance().getManager().getTeam())) {
            player.setPhysical(rule.getRehabilitationCost(player.getOverral(), player.getPhysical()));
            dao.update(player);
        }

        currentBudget.setCurrentCash(currentBudget.getCurrentCash() - PHYSICAL_RECOVERY_COST);
        daoBudget.update(currentBudget);

        Toast.makeText(getApplicationContext(), "Físico recuperado com sucesso!", Toast.LENGTH_LONG);
        startActivity(new Intent(getApplicationContext(), TeamActivity.class));
    }

}