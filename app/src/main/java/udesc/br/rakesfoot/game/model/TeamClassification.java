package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;

public class TeamClassification extends Entity{

    private Team team;

    private int  position,     // Position on the table
                 victories,    // Number of victories
                 losses,       // Number of losses
                 draws,        // Number of draws
                 goalsPro,     // Goals Pro
                 goalsAgainst, // Goals Against
                 goalsBalance, // Goals Balance
                 points;       // Points

    public TeamClassification() {
    }

    public Team getTeam() {
        if(team == null) {
            team = new Team();
        }
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getGoalsPro() {
        return goalsPro;
    }

    public void setGoalsPro(int goalsPro) {
        this.goalsPro = goalsPro;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsBalance() {
        return goalsBalance;
    }

    public void setGoalsBalance(int goalsBalance) {
        this.goalsBalance = goalsBalance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}