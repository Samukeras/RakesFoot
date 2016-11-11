package udesc.br.rakesfoot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.util.DialogUtils;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.rules.Formation;

public class TeamActivity extends TableActivity {

    private TextView textTotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAll();
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_team);
    }

    @Override
    protected void startComponents() {
        table      = (TableLayout) findViewById(R.id.tbPlayer);
        textTotals = (TextView) findViewById(R.id.textTotals);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadTable();
        checkFormation();
    }

    private void checkFormation() {
        Player[] players = new Player[Game.getTeam().getFormation().getFirstTeamPlayers().size()];
        players = Game.getTeam().getFormation().getFirstTeamPlayers().toArray(players);

        for (Player player : players) {
            ((CompoundButton) findViewById(player.getId())).setChecked(true);
        }

        players = new Player[Game.getTeam().getFormation().getSubstitutes().size()];
        players = Game.getTeam().getFormation().getSubstitutes().toArray(players);
        for (Player player : players) {
            ((CompoundButton) findViewById(player.getId() + 10000)).setChecked(true);
        }
    }

    protected void loadTable() {
        int id = 0;
        Team team = Game.getInstance().getManager().getTeam();

        table.removeAllViews();
        createHeader();

        for (Player player : team.getPlayers()) {
            // Create a TableRow and give it an ID
            TableRow tr = new TableRow(this);
//            tr.setBackgroundColor(Color.YELLOW.getColor());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));

            // Create a TextView to house the name of the province
            tr.addView(createText(player.position().getDescription().substring(0, 1), Gravity.CENTER));
            tr.addView(createText(player.getName(), Gravity.LEFT));
            tr.addView(createText(player.getOverral(), Gravity.CENTER));
            tr.addView(createText(player.getPhysical(), Gravity.CENTER));
            tr.addView(createCheckBox(player.getId(), Gravity.CENTER));
            tr.addView(createCheckBox(10000 + player.getId(), Gravity.CENTER));

            // Add the TableRow to the TableLayout
            table.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
        }
    }

    protected void createHeader() {
        TableRow tr = new TableRow(this);
        tr.setBackgroundColor(Color.WHITE.getColor());
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        // Create a TextView to house the name of the province
        tr.addView(createHeaderText("POS", 50, Gravity.CENTER));
        tr.addView(createHeaderText("NOME", 220, Gravity.CENTER));
        tr.addView(createHeaderText("HABIL.", 50, Gravity.CENTER));
        tr.addView(createHeaderText("COND.", 50, Gravity.CENTER));
        tr.addView(createHeaderText("TIT.", 70, Gravity.CENTER));
        tr.addView(createHeaderText("RES.", 70, Gravity.CENTER));

        // Add the TableRow to the TableLayout
        table.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
    }

    private CheckBox createCheckBox(int id, int align) {
        CheckBox check = new CheckBox(this);
        check.setId(id);
        check.setGravity(align);
        check.setOnCheckedChangeListener(getCheckListener());
        check.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        return check;
    }

    private CompoundButton.OnCheckedChangeListener getCheckListener() {
        return (new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int id = buttonView.getId();
                boolean firstTeam  = buttonView.getId() < 10000;
                udesc.br.rakesfoot.game.model.Formation formation = Game.getTeam().getFormation();

                if (!firstTeam) {
                    id -= 10000;
                }

                Player player = Game.getTeam().getPlayer(id);
                boolean hasPlayer = formation.hasPlayer(player);

                if (isChecked && hasPlayer) {
                    if (!firstTeam) {
                        ((CompoundButton) findViewById(id)).setChecked(false);
                        formation.removeFirstTeamPlayer(player);
                    } else {
                        ((CompoundButton) findViewById(id + 10000)).setChecked(false);
                        formation.removeSubstitute(player);
                    }
                }

                String type, baseText = "Falha";

                type = "reservas";
                if (firstTeam) {
                    type = "titulares";
                }

                if (isChecked && !hasPlayer) {
                    baseText = "%s adicionado aos %s";
                    if (!firstTeam) {
                        formation.addSubstitute(player);
                    } else {
                        formation.addFirstTeamPlayer(player);
                    }
                    Toast.makeText(getBaseContext(), String.format(baseText, player.getName(), type), Toast.LENGTH_SHORT).show();
                } else if (hasPlayer && !isChecked){
                    baseText = "%s removido dos %s";
                    if (!firstTeam) {
                        formation.removeSubstitute(player);
                    } else {
                        formation.removeFirstTeamPlayer(player);
                    }
                }

                hasPlayer = firstTeam && formation.hasFirstTeamPlayer(player);
                hasPlayer = hasPlayer || (!firstTeam && formation.hasSubstitutePlayer(player));

                if ((isChecked && hasPlayer) || (!hasPlayer && !isChecked)) {
//                    Toast.makeText(getBaseContext(), String.format(baseText, player.getName(), type), Toast.LENGTH_SHORT).show();
                }

                textTotals.setText(getFormationTotalsMessage());
            }
        });
    }

    private String getFormationTotalsMessage() {
        String totalsText = "Titulares: %s/%s Reservas %s/%s";
        udesc.br.rakesfoot.game.model.Formation formation = Game.getTeam().getFormation();

        return String.format(
                totalsText,
                formation.getFirstTeamPlayers().size(),
                Formation.FIRST_TEAM_COUNT,
                formation.getSubstitutes().size(),
                Formation.SUBSTITUTE_LIMIT
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuStadium:
                startActivity(new Intent(getApplicationContext(), StadiumActivity.class));
            break;
            case R.id.menuPhysicalRecovery:
                startActivity(new Intent(getApplicationContext(), PhysicalRecoveryActivity.class));
            break;
            case R.id.menuPlay:
                if (Game.getTeam().getFormation().isReady()) {
                    startActivity(new Intent(getApplicationContext(), PlayActivity.class));
                } else {
                    Toast.makeText(getBaseContext(), getFormationTotalsMessage(), Toast.LENGTH_LONG).show();
                    DialogUtils.alert(TeamActivity.this, "Atenção!", "Para que o jogo possa iniciar, o time deve possuir 11 titulares sendo um deles goleiro. Também há um limite de " + Formation.SUBSTITUTE_LIMIT + " reservas.");
                }
            break;
            case R.id.menuClassification:
                startActivity(new Intent(getApplicationContext(), ClassificationActivity.class));
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void finishAll() {
        for(Activity activity : activities) {
            if(!(activity instanceof TeamActivity)){
                activity.finish();
            }
        }
    }

}