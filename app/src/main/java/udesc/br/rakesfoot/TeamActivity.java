package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

public class TeamActivity extends AppCompatActivity {

    TableLayout tbPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        tbPlayer = (TableLayout) findViewById(R.id.tbPlayer);
        loadTable();

        setTitle(Game.getInstance().getManager().getTeam().getName());
    }

    private void loadTable() {
        int id = 0;
        Team team = Game.getInstance().getManager().getTeam();

        for (Player player : team.getPlayers()) {
            // Create a TableRow and give it an ID
            TableRow tr = new TableRow(this);
            tr.setBackgroundColor(Color.YELLOW.getColor());
            tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

            // Create a TextView to house the name of the province
            tr.addView(createText(player.position().getDescription().substring(0,1), 50, Gravity.CENTER));
            tr.addView(createText(player.getName(), 200, Gravity.LEFT));
            tr.addView(createText(player.getOverral(), 50, Gravity.RIGHT));
            tr.addView(createText(player.getPhysical(), 50, Gravity.RIGHT));
            tr.addView(createCheckBox(id + 500, 50, Gravity.RIGHT));
            tr.addView(createCheckBox(id + 550, 50, Gravity.RIGHT));

            // Add the TableRow to the TableLayout
            tbPlayer.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
        }
    }

    private TextView createText(int text, int width, int align) {
        return createText(String.valueOf(text), width, align);
    }

    private TextView createText(String text, int width, int align) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(20);
        view.setMinWidth(width);
        view.setTextColor(Color.BLACK.getColor());
        view.setGravity(align);
        view.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        return view;
    }

    private CheckBox createCheckBox(int id, int width, int align) {
        CheckBox check = new CheckBox(this);
        check.setId(id);
        check.setMinWidth(width);
        check.setMinHeight(width);
        check.setGravity(align);
        check.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        return check;
    }

}
