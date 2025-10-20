package sk.ukf.demo.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectType, Object id) {
        super(objectType + " with ID " + id + " was not found.");
    }
    public ObjectNotFoundException(String objectType, String firstName, String lastName) {
        super(objectType + " with name " + firstName + " and surname " + lastName + " was not found.");
    }
}