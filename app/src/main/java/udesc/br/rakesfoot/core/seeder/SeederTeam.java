package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.core.util.NameUtils;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Game;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionshipTeam;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoTeam;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederTeam extends EntitySeeder<Team, Championship> {

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
    public void seed(Championship parent) {
        Season season = Game.getInstance().getCurrentSeason();

        int start = 0,
              end = 20;

        boolean division_1 = parent.type() == ChampionshipType.DIVISION_1;

        if (!division_1) {
            start = 20;
            end   = 40;
        }

        for(int i = start; i < end; i++) {
            Team team = new Team();
            team.setId(i);
            if(division_1) {
                team.setChemestry(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
                team.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(60, 80));
            } else {
                team.setChemestry(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
                team.setMotivation(IntRandomUtils.getNextIntFromValueToInterval(40, 60));
            }

            team.setName(teamsNames[i]);
            team.setMainColor(Color.getRandomColor());
            team.setStadium(new Stadium());
            team.getStadium().setId(team.getId());

            Color secondary;
            do {
                secondary = Color.getRandomColor();
            } while (secondary.getHexadecimal() == team.getMainColor().getHexadecimal());
            team.setSecondaryColor(secondary);

            parent.addTeams(team);
            getDao().insert(team);

            handle(team);
        }
    }

    @Override
    public void crop(Championship parent) {

        SqliteDaoChampionshipTeam dao = new SqliteDaoChampionshipTeam(getConnection().getContext());

        for (Team team : dao.getAllTeams(parent)) {
            if (team.getId() == Game.getTeam().getId()) {
                team = Game.getTeam();
            } else {
                getDao().persists(team);
            }
            parent.addTeams(team);

            handle(team);
        }
    }
}