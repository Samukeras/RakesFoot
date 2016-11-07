package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.core.util.NameUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederTeam extends EntitySeeder {

    private static String[] teamsNames = {
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

    @Override
    public Persistible getDao() {
        return new SqliteDaoTeam(getConnection().getContext());
    }

    @Override
    public void seed(Object parent) {
        Season season = Game.getInstance().getCurrentSeason();

        for(int i = 0; i < teamsNames.length; i++) {
            Team team = new Team();
            team.setId(i);
            if(i < 20) {
                team.setChemestry(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
                team.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
                season.getChampionship(ChampionshipType.DIVISION_1).addTeams(team);
            } else {
                team.setChemestry(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
                team.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
                season.getChampionship(ChampionshipType.DIVISION_2).addTeams(team);
            }

            team.setName(teamsNames[i]);
            team.setMainColor(Color.getRandomColor());
            team.setStadium(new Stadium());
            team.getStadium().setId(team.getId());

            Color secondary;
            do {
                secondary = Color.getRandomColor();
            } while (secondary.getHexadecimal() != team.getMainColor().getHexadecimal());

            team.setSecondaryColor(secondary);

            getDao().insert(team);

            handle(team);
        }
    }

    @Override
    public void crop(Object parent) {

    }
}