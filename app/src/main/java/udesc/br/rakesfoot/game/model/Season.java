package udesc.br.rakesfoot.game.model;

/**
 * Season entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public class Season {

    private int year;


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

}