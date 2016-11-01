package udesc.br.rakesfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import udesc.br.rakesfoot.core.seeder.SeederPlayer;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        teste();
    }

    public void teste() {
        Connection cnx = SQLiteConnection.getInstance(this.getApplicationContext(), Connection.CURRENT_VERSION);
        SeederPlayer sp = new SeederPlayer(cnx);
    }
}
