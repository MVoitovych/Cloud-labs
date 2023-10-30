package ua.lviv.iot.exeption;

public class CallNotFoundException extends RuntimeException {
    public CallNotFoundException(Integer id) {
        super("Could not find 'Call' with id=" + id);
    }
}
