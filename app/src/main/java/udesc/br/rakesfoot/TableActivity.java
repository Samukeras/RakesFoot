package udesc.br.rakesfoot;

import android.graphics.Typeface;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import udesc.br.rakesfoot.core.model.Color;

public abstract class TableActivity extends GameActivity {

    protected TableLayout table;


    protected abstract void loadTable();

    protected abstract void createHeader();

    @Override
    protected void startComponents() {
        table = (TableLayout) findViewById(R.id.tbPlayer);

        loadTable();
    }

    protected TextView createHeaderText(String text, int width, int align) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(15);
        view.setMinWidth(width);
        view.setTextColor(Color.BLACK.getColor());
        view.setTypeface(null, Typeface.BOLD);
        view.setGravity(align);
        view.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        return view;
    }


    protected TextView createText(int text, int align) {
        return createText(String.valueOf(text), align);
    }

    protected TextView createText(String text, int align) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(getDefaultTextSize());
        view.setTextColor(Color.BLACK.getColor());
        view.setGravity(align);
        view.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        return view;
    }

    protected int getDefaultTextSize() {
        return 18;
    }

}