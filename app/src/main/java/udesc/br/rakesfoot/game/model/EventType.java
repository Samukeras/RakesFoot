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

    public String getDescription() {
        return description;
    }
}