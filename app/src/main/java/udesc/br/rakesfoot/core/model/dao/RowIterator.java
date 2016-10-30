package udesc.br.rakesfoot.core.model.dao;

import java.util.Iterator;

import udesc.br.rakesfoot.core.util.Countable;

/**
 *
 * @author Samuel Felício Adriano
 * @param <Entity>
 */
public interface RowIterator<Entity extends udesc.br.rakesfoot.core.model.Entity> extends Iterator<Entity>, Countable, Iterable<Entity> {

    public void rewind();

}