package udesc.br.rakesfoot.game.model;

/**
 * Positions enumeration types
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public enum Position {

     GOALKEEPER(1, "Goalkeeper")
    ,DEFENDER  (2, "Defensor")
    ,MIDFIELDER(3, "Midfielder")
    ,FORWARD   (4, "Forward");


    private int    id;
    private String description;


    Position(int id, String description) {
        this.id = id;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}