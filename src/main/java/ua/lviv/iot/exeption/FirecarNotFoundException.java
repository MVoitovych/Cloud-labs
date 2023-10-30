package ua.lviv.iot.exeption;

public class FirecarNotFoundException extends RuntimeException{
    public FirecarNotFoundException(Integer id) {
        super("Could not find 'Firecar' with id=" + id);
    }
}
