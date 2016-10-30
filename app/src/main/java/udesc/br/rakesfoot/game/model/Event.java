package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;

/**
 * Created by felic on 30/10/2016.
 */
public class Event extends Entity{

    private int   id;
    private Match match;
    private int   minute;
    private Player player;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
