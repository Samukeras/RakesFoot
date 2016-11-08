package udesc.br.rakesfoot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import udesc.br.rakesfoot.core.seeder.SeederGame;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.core.util.connection.SQLiteConnection;
import udesc.br.rakesfoot.game.model.Game;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }

    public void startNewGame(View view) {
        Game.getInstance().getManager().setName(((TextView) findViewById(R.id.textName)).getText().toString());
        SeederGame seeder = new SeederGame();
        seeder.setConnection(SQLiteConnection.getInstance(getBaseContext()));
        seeder.start();

        startActivity(new Intent(getBaseContext(), TeamActivity.class));
    }

    public void deleteGame(View view) {
        SQLiteConnection.deleteDataBase(getBaseContext());
        SQLiteConnection.getInstance(getBaseContext()).versionHandler(Connection.INITIAL_VERSION);

        AlertDialog.Builder dialog = new AlertDialog.Builder(ManagerActivity.this);

        dialog.setTitle("Sucesso");
        dialog.setMessage("Todos os dados foram apagados!");
        dialog.setIcon(android.R.drawable.ic_menu_delete);
        dialog.setCancelable(true);

        dialog.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        dialog.create();
        dialog.show();
    }
}
