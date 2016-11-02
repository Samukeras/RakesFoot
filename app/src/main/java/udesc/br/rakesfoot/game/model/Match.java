package udesc.br.rakesfoot.game.model;

/**
 * Created by felic on 30/10/2016.
 */
public class Match {

    private int i;

    private Championship championship;

    private Team host;

    private Team guest;

    private int audience;

    private int round; // rodada

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
}
