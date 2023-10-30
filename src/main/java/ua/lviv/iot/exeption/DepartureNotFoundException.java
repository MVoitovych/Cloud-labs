package ua.lviv.iot.exeption;

public class DepartureNotFoundException extends RuntimeException {
    public DepartureNotFoundException(Integer id) {
        super("Could not find 'Departure' with id=" + id);
    }
}
