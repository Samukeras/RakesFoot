package udesc.br.rakesfoot.game.model;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.annotation.Table;

/**
 * Created by felic on 29/10/2016.
 */
@Table(name = "stadium")
public class Stadium extends Entity {

    private int    id,
                   maxCapacity;
    private String name;


    public Stadium() {
        this(0, 0, null);
    }

    public Stadium(int id, int maxCapacity, String name) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}