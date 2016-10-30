package udesc.br.rakesfoot.core.persistence;

import java.util.Iterator;

/**
 *
 * @author Ricardo Augusto Küstner
 * @param <Entity>
 */
public interface Persistible<Entity extends udesc.br.rakesfoot.core.model.Entity> {

    public boolean insert(Entity entity);

    public boolean delete(Entity entity);

    public boolean update(Entity entity);

    public Iterable<Entity> getAll();

    public boolean persists(Entity entity);

    public boolean exists(Entity entity);

    public Entity findOne(Entity entity);

    public Iterator<Entity> findAll(Entity entity);

    public Entity getNewEntity();

    public void onCreate();

    public void onUpgrade();

}