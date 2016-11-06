package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Created by felic on 30/10/2016.
 */
@Table(name = "event")
public class Event extends Entity{

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER, sequential = true)
    private int   id;

    @DataBaseInfo(columnName = "description", dataType = STRING_VARCHAR)
    private int   minute;

    @DataBaseInfo(columnName = "match_id", dataType = STRING_VARCHAR)
    private Match match;

    @DataBaseInfo(columnName = "player_id", dataType = INT_INTEGER)
    private Player player;

    @DataBaseInfo(columnName = "team_id", dataType = INT_INTEGER)
    private Team team;

    @DataBaseInfo(columnName = "type", dataType = INT_INTEGER)
    private EventType type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = EventType.getEventType(type);
    }

}
