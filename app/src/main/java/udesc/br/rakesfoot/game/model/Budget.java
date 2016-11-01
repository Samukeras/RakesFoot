package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;

/**
 * Created by Ricardo on 31/10/2016.
 */

public class Budget {

    private Team team;

    private Season season;

    private double startCash;

    private ArrayList<BudgetEntry> entries;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public double getStartCash() {
        return startCash;
    }

    public void setStartCash(double startCash) {
        this.startCash = startCash;
    }

    public ArrayList<BudgetEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<BudgetEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(BudgetEntry entry) {
        this.entries.add(entry);
    }
}
