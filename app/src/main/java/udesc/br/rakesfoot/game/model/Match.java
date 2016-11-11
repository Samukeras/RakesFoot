package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;
import java.util.List;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Created by felic on 30/10/2016.
 */
@Table(name = "match")
public class Match extends Entity {

    @DataBaseInfo(key = true, columnName = "id", dataType = INT_INTEGER, sequential = true)
    private int id;

    @DataBaseInfo(columnName = "championship_id", dataType = INT_INTEGER)
    private Championship championship;

    @DataBaseInfo(columnName = "host_id", dataType = INT_INTEGER)
    private Team host;

    @DataBaseInfo(columnName = "guest_id", dataType = INT_INTEGER)
    private Team guest;

    @DataBaseInfo(columnName = "audience", dataType = INT_INTEGER)
    private int audience;

    @DataBaseInfo(columnName = "round", dataType = INT_INTEGER)
    private int round; // rodada

    @DataBaseInfo(columnName = "winner", dataType = INT_INTEGER)
    private Result winner;

    private List<Event> events = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Championship getChampionship() {
        if (this.championship == null) {
            this.championship = new Championship();
        }
        return championship;
    }

    public int getChampionshipId() {
        return getChampionship().getId();
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Team getHost() {
        if (this.host == null) {
            this.host = new Team();
        }
        return host;
    }

    public int getHostId() {
        return getHost().getId();
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getGuest() {
        if (this.guest == null) {
            this.guest = new Team();
        }
        return guest;
    }

    public int getGuestId() {
        return getGuest().getId();
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }

    public int getEventCount(EventType type) {
        int count = 0;
        for (Event event : events) {
            if (event.type() == type) {
                count++;
            }
        }
        return count;
    }

    public int getEventCount(EventType type, Player player) {
        int count = 0;
        for (Event event : events) {
            if (event.type() == type && player.getId() == event.getPlayer().getId()) {
                count++;
            }
        }
        return count;
    }

    public int getEventCount(EventType type, Team team) {
        int count = 0;
        for (Event event : events) {

            if (event.type() == type && team.getId() == event.getTeam().getId()) {
                count++;
            }
        }
        return count;
    }

    public Result getWinner() {
        return winner;
    }

    public void setWinner(Result winner) {
        this.winner = winner;
    }

    public void setWinner(int winner) {
        this.winner = Result.getResultById(winner);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
