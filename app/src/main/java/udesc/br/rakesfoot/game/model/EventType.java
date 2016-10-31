package udesc.br.rakesfoot.game.model;

/**
 * Created by felic on 30/10/2016.
 */
public enum EventType {

     GOAL       (1, "Goal")
    ,ASSISTANCE (2, "Assistance")
    ,CLEAN_SHEET(3, "Clean Sheet")
    ,YELLOW_CARD(4, "Yellow Card")
    ,RED_CARD   (4, "Red Card");

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

    public static EventType getEventType(int id) {
        for(EventType eventType : EventType.values()) {
            if(eventType.getId() == id) {
                return eventType;
            }
        }

        return null;
    }

}