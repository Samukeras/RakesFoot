package udesc.br.rakesfoot.game.model;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Season entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
@Table(name = "season")
public class Season extends Entity {

    @DataBaseInfo(key = true, columnName = "year", dataType = INT_INTEGER)
    private int year;

    private List<Championship> championships = new ArrayList<>();

    public Season() {
        this(0);
    }

    public Season(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Championship> getChampionships() {
        return championships;
    }

    public boolean addCaChampionship(Championship championship) {
        return championships.add(championship);
    }

    public Championship getChampionship(ChampionshipType type) {
        for (Championship championship : championships) {
            if (championship.type() == type) {
                return championship;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }
}
