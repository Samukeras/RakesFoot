package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;

public class TeamClassification extends Entity{

    private Team team;

    private int  position,  // Position on the table
                 victories, // Number ofvictories
                 defeats,   // Number of defeats
                 draws,     // Number of draws
                 gp,        // Goals Pro
                 ga,        // Goals Against
                 gb,        // Goals Balance
                 points;

    public TeamClassification(Team team, int position, int victories, int defeats, int draws, int gp, int ga, int gb, int points) {
        this.team = team;
        this.position = position;
        this.victories = victories;
        this.defeats = defeats;
        this.draws = draws;
        this.gp = gp;
        this.ga = ga;
        this.gb = gb;
        this.points = points;
    }

}