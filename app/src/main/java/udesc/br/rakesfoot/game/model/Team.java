package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.DOUBLE_NUMERIC;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Team entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
@Table(name = "team")
public class Team extends Entity {

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER)
    private int    id;

    @DataBaseInfo(columnName = "chemestry", dataType = DOUBLE_NUMERIC)
    private double chemestry;

    @DataBaseInfo(columnName = "motivation", dataType = DOUBLE_NUMERIC)
    private double motivation;

    @DataBaseInfo(columnName = "name", dataType = STRING_VARCHAR)
    private String name;

    @DataBaseInfo(columnName = "initials", dataType = STRING_VARCHAR)
    private String initials;

    @DataBaseInfo(columnName = "mainColor", dataType = INT_INTEGER)
    private Color  mainColor;

    @DataBaseInfo(columnName = "secondaryColor", dataType = INT_INTEGER)
    private Color  secondaryColor;


    private Stadium stadium;

    private Formation formation;

    private List<Player> players = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getChemestry() {
        return chemestry;
    }

    public void setChemestry(double chemestry) {
        this.chemestry = chemestry;
    }

    public double getMotivation() {
        return motivation;
    }

    public void setMotivation(double motivation) {
        this.motivation = motivation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public void setMainColor(int mainColor) {
        this.mainColor = Color.getColor(mainColor);
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.mainColor = Color.getColor(secondaryColor);
    }

    public Stadium getStadium() {
        if (stadium == null) {
            stadium = new Stadium();
        }
        return stadium;
    }

    public int getStadiumId() {
        return getStadium().getId();
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }

        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        formation.removeFirstTeamPlayer(player);
        formation.removeSubstitute(player);
    }

    public Formation getFormation() {
        if (formation == null) {
            formation = new Formation();
        }
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}