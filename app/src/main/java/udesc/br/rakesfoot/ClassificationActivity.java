package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

public class ClassificationActivity extends TableActivity {

    TableLayout tbClassification;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_classification);
    }

    @Override
    protected void startComponents() {
        table = (TableLayout) findViewById(R.id.tbClassification);

        loadTable();
    }

    protected void loadTable() {
        createHeader();
    }

    protected void createHeader() {
        TableRow tr = new TableRow(this);
        tr.setBackgroundColor(Color.WHITE.getColor());
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        // Create a TextView to house the name of the province
        tr.addView(createHeaderText("POS" ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("Time", 220, Gravity.CENTER));
        tr.addView(createHeaderText("V"   ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("D"   ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("E"   ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("GP"  ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("GC"  ,  50, Gravity.CENTER));
        tr.addView(createHeaderText("SG"  ,  70, Gravity.CENTER));
        tr.addView(createHeaderText("PTS" ,  70, Gravity.CENTER));

        // Add the TableRow to the TableLayout
        table.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
    }

}