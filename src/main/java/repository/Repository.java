package repository;

import domain.Entity;


public interface Repository<ID,E extends Entity<ID>> {

    /**
     * @param id - the id of the entity to be returned
     * @return the entity with the specified id or null if there's no such entity
     */
    E findOne(ID id);

    /**
     * @return all the entities found
     */
    Iterable<E> getAll();

    /**
     * @param entity - the entity that must be added
     * @return null if the entity was successfully saved or the entity
     * if it already exists
     */
    E add(E entity);

    /**
     * @param id - the id of the entity which must be deleted
     * @return the removed entity or null if there's no entity
     * with the specified id
     */
    E delete(ID id);

    /**
     * @param entity - the entity that needs to be changed with
     *                 the modified fields
     * @return null if the entity was modified or the entity if
     * there's no entity with the specified id
     */
    E update(E entity);

}
