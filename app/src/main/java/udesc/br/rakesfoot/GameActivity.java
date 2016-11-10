package udesc.br.rakesfoot;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import udesc.br.rakesfoot.game.model.Game;

public abstract class GameActivity extends AppCompatActivity {

    protected static ArrayList<Activity> activities = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        setLayout();

        setTitleActionBar();
        alterColorActionBar();
        startComponents();
    }

    protected void setTitleActionBar() {
        setTitle(Game.getTeam().getName());
    }

    protected void alterColorActionBar() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        if(bar != null && Game.getTeam().getMainColor() != null) {
            bar.setBackgroundDrawable(new ColorDrawable(Game.getTeam().getMainColor().getColor()));
        }
    }

    protected abstract void setLayout();

    protected abstract void startComponents();

    @Override
    public void onDestroy() {
        activities.remove(this);
        super.onDestroy();
    }

    public static void finishAll() {
        for(Activity activity : activities) {
            activity.finish();
        }
    }

}