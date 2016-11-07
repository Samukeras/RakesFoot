package udesc.br.rakesfoot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import udesc.br.rakesfoot.core.seeder.SeederGame;
import udesc.br.rakesfoot.core.seeder.SeederPlayer;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickJogar(View v) {
        startActivity(new Intent(getBaseContext(), ManagerActivity.class));
    }

}
