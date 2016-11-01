package udesc.br.rakesfoot.game.model;

import android.support.annotation.Nullable;

/**
 * Created by felic on 30/10/2016.
 */
public enum EventType {

     GOAL       (1, "Gol")
    ,ASSISTANCE (2, "Assistência")
    ,INJURY     (3, "Lesão")
    ,YELLOW_CARD(4, "Cartão Amarelo")
    ,RED_CARD   (5, "Cartão Vermelho");

    private int    id;
    private String description;

    EventType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Nullable
    public static EventType getEventType(int id) {
        for(EventType eventType : EventType.values()) {
            if(eventType.getId() == id) {
                return eventType;
            }
        }

        return null;
    }

}