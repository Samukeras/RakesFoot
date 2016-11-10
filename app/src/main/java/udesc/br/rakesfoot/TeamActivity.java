package udesc.br.rakesfoot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

public class TeamActivity extends TableActivity {

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
        table = (TableLayout) findViewById(R.id.tbPlayer);

        loadTable();
    }

    protected void loadTable() {
        int id = 0;
        Team team = Game.getInstance().getManager().getTeam();

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
            tr.addView(createText(player.getOverral(), Gravity.RIGHT));
            tr.addView(createText(player.getPhysical(), Gravity.RIGHT));
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
        check.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        return check;
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
                startActivity(new Intent(getApplicationContext(), PlayActivity.class));
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