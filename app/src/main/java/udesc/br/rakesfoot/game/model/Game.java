package udesc.br.rakesfoot.game.model;

/**
 * Created by Ricardo on 06/11/2016.
 */
public class Game {
    private static Game ourInstance = new Game();

    private Season currentSeason;

    private Manager manager;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {}

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
