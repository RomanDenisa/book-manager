package domain;

/**
 * identifiable entity - has an unique ID
 * @param <ID> the id of the entity
 */
public class Entity<ID> {

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
