package pl.com.bottega.cms.model;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity, Object id) {
        super(String.format("Entity: '%s' of id: '%s' does not exist", entity, id));
    }

}
