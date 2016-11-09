package udesc.br.rakesfoot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

public class PhysicalRecoveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_recovery);
    }

    public void onClickRecoverAll(View view) {
        SqliteDaoPlayer dao = new SqliteDaoPlayer(getApplicationContext());
        udesc.br.rakesfoot.game.rules.Player rule = new udesc.br.rakesfoot.game.rules.Player();

        for(Player player : dao.getAllPlayers(Game.getInstance().getManager().getTeam())) {
            player.setPhysical(rule.getRehabilitationCost(player.getOverral(), player.getPhysical()));
            dao.update(player);
        }

        Toast.makeText(getApplicationContext(), "Físico recuperado com sucesso!", Toast.LENGTH_LONG);
        startActivity(new Intent(getApplicationContext(), TeamActivity.class));
    }

}
