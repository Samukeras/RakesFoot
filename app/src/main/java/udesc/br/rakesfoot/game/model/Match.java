package udesc.br.rakesfoot.game.model;

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
}
