package lt.viko.eif.rcepauskas.restservice.repositories;

import java.util.List;

public interface IRepository<T> {
    /**
     * gets a single entity by id
     * @param id entity id
     * @param <T> entity class
     * @return a single entity
     */
    <T> Object get(Integer id);

    /**
     * gets all entities
     * @param <T> entity class
     * @return a list of entities
     */
    <T> List<Object> getAll();

    /**
     * inserts a new entity
     * @param t entity object
     */
    void insert(T t);

    /**
     * updates provided entity
     * @param t entity object
     */
    void update(T t);

    /**
     * deletes an entity by id
     * @param id entity id
     */
    void delete(Integer id);
}
