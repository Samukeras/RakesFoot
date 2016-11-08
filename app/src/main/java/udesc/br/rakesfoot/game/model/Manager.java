package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.INT_INTEGER;
import static udesc.br.rakesfoot.core.persistence.EntityDataBaseTypeRelation.STRING_VARCHAR;

/**
 * Created by felic on 29/10/2016.
 */
@Table(name = "manager")
public class Manager extends Entity {

    @DataBaseInfo(columnName = "name", dataType = STRING_VARCHAR)
    private String name;

    @DataBaseInfo(columnName = "team_id", dataType = INT_INTEGER)
    private Team team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        if (this.team == null) {
            this.team = new Team();
        }
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}