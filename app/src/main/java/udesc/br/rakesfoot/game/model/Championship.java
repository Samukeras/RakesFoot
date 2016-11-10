package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.Entity;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.*;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

/**
 * Championship entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
@Table(name = "championship")
public class Championship extends Entity {

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER, sequential = true)
    private int    id;

    @DataBaseInfo(columnName = "name", dataType = STRING_VARCHAR)
    private String name;

    @DataBaseInfo(columnName = "season", dataType = INT_INTEGER)
    private Season season;

    @DataBaseInfo(columnName = "type", dataType = INT_INTEGER)
    private ChampionshipType type;

    @DataBaseInfo(columnName = "round", dataType = INT_INTEGER)
    private int round;

    private List<Team> teams = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Season getSeason() {
        if (this.season == null) {
            this.season = new Season();
        }
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public boolean addTeams(Team team) {
        return teams.add(team);
    }

    public Team getTeam(int id) {
        for (Team team : teams) {
            if (team.getId() == id) {
                return team;
            }
        }
        return null;
    }

    public boolean hasTeam(Team team) {
        return teams.contains(team);
    }

    public int getType() {
        return type.getValue();
    }

    public ChampionshipType type() {
        return type;
    }

    public void setType(ChampionshipType type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = ChampionshipType.getChampionshipType(type);
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}