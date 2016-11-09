package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    }

}
