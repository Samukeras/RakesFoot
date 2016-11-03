package udesc.br.rakesfoot.game.model;

import java.util.List;

import udesc.br.rakesfoot.core.persistence.annotation.Table;

/**
 * Created by felic on 30/10/2016.
 */
@Table(name = "match")
public class Match {

    private int i;

    private Championship championship;

    private Team host;

    private Team guest;

    private int audience;

    private int round; // rodada

    private List<Event> events;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Team getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getGuest() {
        return guest;
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
            if (event.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public int getEventCount(EventType type, Player player) {
        int count = 0;
        for (Event event : events) {
            if (event.getType() == type && player.getId() == event.getPlayer().getId()) {
                count++;
            }
        }
        return count;
    }

    public int getEventCount(EventType type, Team team) {
        int count = 0;
        for (Event event : events) {

            if (event.getType() == type && team.getId() == event.getTeam().getId()) {
                count++;
            }
        }
        return count;
    }
}
