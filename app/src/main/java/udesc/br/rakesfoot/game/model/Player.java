package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Player entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
@Table(name = "player")
public class Player extends Entity {

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER, sequential = true)
    private int      id;

    @DataBaseInfo(columnName = "overral", dataType = INT_INTEGER)
    private int      overral;

    @DataBaseInfo(columnName = "physical", dataType = INT_INTEGER)
    private int      physical;

    @DataBaseInfo(columnName = "motivation", dataType = INT_INTEGER)
    private int      motivation;

    @DataBaseInfo(columnName = "name", dataType = STRING_VARCHAR)
    private String   name;

    @DataBaseInfo(columnName = "position", dataType = INT_INTEGER)
    private Position position;

    @DataBaseInfo(columnName = "team_id", dataType = INT_INTEGER)
    private Team team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOverral() {
        return overral;
    }

    public void setOverral(int overral) {
        this.overral = overral;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position.getValue();
    }

    public Position position() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = Position.getPosition(position);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTeamId() {
        return getTeam().getId();
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}