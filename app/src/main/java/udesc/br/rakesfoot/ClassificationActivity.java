package udesc.br.rakesfoot;

import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.game.model.TeamClassification;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoClassification;

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

        int position = 0;
        SqliteDaoClassification dao = new SqliteDaoClassification(getApplicationContext());
        for(TeamClassification classification : dao.getAll()) {
            classification.setPosition(++position);
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                                         TableRow.LayoutParams.MATCH_PARENT));

            tr.addView(createText(classification.getPosition()      , Gravity.CENTER));
            tr.addView(createText(classification.getTeam().getName(), Gravity.CENTER));
            tr.addView(createText(classification.getVictories()     , Gravity.CENTER));
            tr.addView(createText(classification.getLosses()        , Gravity.CENTER));
            tr.addView(createText(classification.getDraws()         , Gravity.CENTER));
            tr.addView(createText(classification.getGoalsPro()      , Gravity.CENTER));
            tr.addView(createText(classification.getGoalsAgainst()  , Gravity.CENTER));
            tr.addView(createText(classification.getGoalsBalance()  , Gravity.CENTER));
            tr.addView(createText(classification.getPoints()        , Gravity.CENTER));
        }
    }

    protected void createHeader() {
        TableRow tr = new TableRow(this);
        tr.setBackgroundColor(Color.WHITE.getColor());
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

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