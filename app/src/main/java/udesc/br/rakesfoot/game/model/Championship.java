package udesc.br.rakesfoot.game.model;

/**
 * Championship entity
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public class Championship {

    private int    id;
    private String name;
    private Season season;


    public Championship() {
        this(0, null, null);
    }

    public Championship(int id, String name, Season season) {
        this.id = id;
        this.name = name;
        this.season = season;
    }


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
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

}