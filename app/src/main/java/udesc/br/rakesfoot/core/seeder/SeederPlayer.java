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
public class SeederPlayer extends EntitySeeder<Player, Team> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoPlayer(getConnection().getContext());
    }

    public void insertPlayers(Team team) {
        for(int i = 0; i < 22; i++) {
            Player player = new Player();
            if(team.getId() <= 20) {
                player.setOverral(IntRandomUtils.getNextIntFromValueToInterval(70, 90));
                player.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(80, 99));
            } else {
                player.setOverral(IntRandomUtils.getNextIntFromValueToInterval(50, 70));
                player.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
            }
            player.setPhysical(100);
            String name     = NameUtils.generateRandomFirstName(getConnection().getContext()),
                   lastName = NameUtils.generateRandomLastName(getConnection().getContext());
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
            team.addPlayer(player);

            getDao().insert(player);

            handle(player);
        }
    }

    @Override
    public void seed(Team parent) {
        insertPlayers(parent);
    }

    @Override
    public void crop(Team parent) {

    }
}