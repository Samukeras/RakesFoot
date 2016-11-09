package udesc.br.rakesfoot;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.core.seeder.SeederGame;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Manager;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoManager;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

public class ManagerActivity extends AppCompatActivity {

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        connection = SQLiteConnection.getInstance(getBaseContext());

        handleVersion();
    }

    private void handleVersion() {
        if (connection.getVersion() == Connection.INITIAL_VERSION) {
            ((TextView) findViewById(R.id.textName)).setText("");
            ((TextView) findViewById(R.id.textTime)).setText("Não definido");
            ((TextView) findViewById(R.id.btnDelete)).setEnabled(false);
            ((TextView) findViewById(R.id.textName)).setEnabled(true);
        } else {
            DAOGeneric dao = new SqliteDaoManager(getBaseContext());
            dao.persists(Game.getInstance().getManager());

            dao = new SqliteDaoTeam(getBaseContext());
            System.out.println(Game.getInstance().getManager().getTeam().getId());
            dao.persists(Game.getInstance().getManager().getTeam());

            ((TextView) findViewById(R.id.textName)).setText(Game.getInstance().getManager().getName());
            ((TextView) findViewById(R.id.textTime)).setText(Game.getInstance().getManager().getTeam().getName());
            ((TextView) findViewById(R.id.textName)).setEnabled(false);
            ((TextView) findViewById(R.id.btnDelete)).setEnabled(true);
        }
    }

    public void playGame(View view) {
        Game.getInstance().getManager().setName(((TextView) findViewById(R.id.textName)).getText().toString());
        SeederGame seeder = new SeederGame();
        seeder.setConnection(connection);

        seeder.start();

        startActivity(new Intent(getBaseContext(), TeamActivity.class));
    }

    public void deleteGame(View view) {
        SQLiteConnection.deleteDataBase(getBaseContext());
        AlertDialog.Builder dialog = new AlertDialog.Builder(ManagerActivity.this);

        dialog.setTitle("Sucesso");
        dialog.setMessage("Todos os dados foram apagados!");
        dialog.setIcon(android.R.drawable.ic_menu_delete);
        dialog.setCancelable(true);

        dialog.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleVersion();
            }
        });

        dialog.create();
        dialog.show();
    }
}
