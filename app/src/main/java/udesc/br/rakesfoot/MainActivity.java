package udesc.br.rakesfoot;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
import udesc.br.rakesfoot.core.seeder.SeederGame;
import udesc.br.rakesfoot.core.seeder.SeederPlayer;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;

public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends Activity {
>>>>>>> 3d15da7b482efb8defe48cbfb0dc61eaf6a7e8d8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        teste();
    }

    public void teste() {
        Connection cnx = SQLiteConnection.getInstance(this.getApplicationContext(), Connection.CURRENT_VERSION);
        SeederGame   sg = new SeederGame(cnx);
    }
}
