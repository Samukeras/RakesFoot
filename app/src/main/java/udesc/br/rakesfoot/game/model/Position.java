package udesc.br.rakesfoot.game.model;

import android.support.annotation.Nullable;

/**
 * Positions enumeration types
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  29/10/2016
 */
public enum Position {

     GOALKEEPER(1, "Goleiro")
    ,DEFENDER  (2, "Defensor")
    ,MIDFIELDER(3, "Meia")
    ,FORWARD   (4, "Atacante");


    private final int value;
    private final String description;


    Position(int value, String description) {
        this.value = value;
        this.description = description;
    }


    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Nullable
    public static Position getPosition(int id) {
        for(Position position : Position.values()) {
            if(position.getValue() == id) {
                return position;
            }
        }

        return null;
    }

}