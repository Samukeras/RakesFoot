package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;

/**
 * Season entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public class Season extends Entity {

    private int year;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}