package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.core.util.NameUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederTeam extends EntitySeeder {

    private static String[] teams = {
             "Palmeiras"
            ,"Flamengo"
            ,"Santos"
            ,"Atlético-MG"
            ,"Botafogo"
            ,"Atlético-PR"
            ,"Corinthians"
            ,"Grêmio"
            ,"Fluminense"
            ,"Ponte Preta"
            ,"Chapecoense"
            ,"São Paulo"
            ,"Cruzeiro"
            ,"Sport"
            ,"Coritiba"
            ,"Internacional"
            ,"Vitória"
            ,"Figueirense"
            ,"América-MG"
            ,"Santa Cruz"
            ,"Atlético-GO"
            ,"Vasco"
            ,"Avaí"
            ,"Náutico"
            ,"Londrina"
            ,"Bahia"
            ,"Luverdense"
            ,"CRB"
            ,"Criciúma"
            ,"Ceará"
            ,"Vila Nova"
            ,"Brasil de Pelotas"
            ,"Goiás"
            ,"Paysandu"
            ,"Paraná"
            ,"Oeste"
            ,"Bragantino"
            ,"Tupi-MG"
            ,"Joinville"
            ,"Sampaio Corrêa"
    };

    public SeederTeam(Connection connection) {
        super(connection);
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoTeam(this.connection.getContext(), Connection.INITIAL_VERSION);
    }

    @Override
    public void seed(Connection connection) {
        Persistible persistible = getDao();
        persistible.onCreate();

        for(int i = 0; i < teams.length; i++) {
            Team team = new Team();
            team.setId(i);
            team.setChemestry(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
            team.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
            team.setName(teams[i]);
            team.setMainColor(Color.getRandomColor());
            team.setSecondaryColor(Color.getRandomColor());
            team.getStadium().setId(i);
        }
    }

}