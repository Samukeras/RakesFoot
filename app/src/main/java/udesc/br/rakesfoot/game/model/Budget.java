package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.DOUBLE_NUMERIC;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;

/**
 * Created by Ricardo on 31/10/2016.
 */
@Table(name = "budget")
public class Budget extends Entity {

    @DataBaseInfo(key = true, columnName = "team_id", dataType = INT_INTEGER, sequential = true)
    private Team team;

    @DataBaseInfo(columnName = "start_cash", dataType = DOUBLE_NUMERIC)
    private double startCash;

    @DataBaseInfo(columnName = "current_cash", dataType = DOUBLE_NUMERIC)
    private double currentCash;

    private ArrayList<BudgetEntry> entries;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getStartCash() {
        return startCash;
    }

    public void setStartCash(double startCash) {
        this.startCash = startCash;
    }

    public double getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(double currentCash) {
        this.currentCash = currentCash;
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
