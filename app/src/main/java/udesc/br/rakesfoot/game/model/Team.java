package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Color;
import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.DOUBLE_NUMERIC;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Team entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public class Team extends Entity {

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER, sequential = true)
    private int    id;

    @DataBaseInfo(columnName = "chemestry", dataType = DOUBLE_NUMERIC)
    private int    chemestry; //Entrosamento

    @DataBaseInfo(columnName = "motivation", dataType = DOUBLE_NUMERIC)
    private int    motivation;

    private String name,
                   initials;
    private Color mainColor,
                   secondaryColor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChemestry() {
        return chemestry;
    }

    public void setChemestry(int chemestry) {
        this.chemestry = chemestry;
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

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

}