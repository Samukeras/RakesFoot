package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.core.util.NameUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Position;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoPlayer;

/**
 * Created by felic on 31/10/2016.
 */
public class SeederPlayer extends EntitySeeder {

    private static int NUMBER = 22;
    private Team[] teams;

    public SeederPlayer(Connection connection, Team[] teams) {
        super(connection);
        this.teams = teams;
        insertPlayers();
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoPlayer(connection.getContext(), Connection.INITIAL_VERSION);
    }

    public void insertPlayers() {
        int numeroTime = 0;

        for(Team team : teams) {
            for(int i = 0; i < NUMBER; i++) {
                Player player = new Player();
                if(numeroTime < 20) {
                    player.setOverral(IntRandomUtils.getNextIntFromValueToInterval(80, 99));
                    player.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(80, 99));
                } else {
                    player.setOverral(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
                    player.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
                }
                player.setPhysical(100);
                String name     = NameUtils.generateRandomFirstName(connection.getContext()),
                       lastName = NameUtils.generateRandomLastName(connection.getContext());
                player.setName(name + " " + lastName);
                if(i > 18) {
                    player.setPosition(Position.FORWARD);
                } else if(i > 10) {
                    player.setPosition(Position.MIDFIELDER);
                } else if(i > 2) {
                    player.setPosition(Position.DEFENDER);
                } else {
                    player.setPosition(Position.GOALKEEPER);
                }
                player.setTeam(team);
            }
        }
    }

    @Override
    public void seed(Connection connection) {
        connection.beginTransaction();
        Persistible persistible = getDao();
        persistible.onCreate();
    }

}