package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Created by Ricardo on 09/11/2016.
 */
@Table(name = "championshipteam")
public class ChampionshipTeam extends Entity {

    @DataBaseInfo(key = true, columnName = "team_id", dataType = INT_INTEGER)
    private Team team;

    @DataBaseInfo(key = true, columnName = "championship_id", dataType = INT_INTEGER)
    private Championship championship;

    public ChampionshipTeam(Team team, Championship championship) {
        this.team = team;
        this.championship = championship;
    }

    public ChampionshipTeam() {}

    public Team getTeam() {
        if (team == null) {
            team = new Team();
        }
        return team;
    }

    public int getTeamId() {
        return getTeam().getId();
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Championship getChampionship() {
        if (championship == null) {
            championship = new Championship();
        }
        return championship;
    }

    public int getChampionshipId() {
        return getChampionship().getId();
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
