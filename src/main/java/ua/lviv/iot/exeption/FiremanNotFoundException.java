package ua.lviv.iot.exeption;

public class FiremanNotFoundException extends RuntimeException{
    public FiremanNotFoundException(Integer id) {
        super("Could not find 'Firecar' with id=" + id);
    }
}
