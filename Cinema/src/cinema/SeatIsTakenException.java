package cinema;

public class SeatIsTakenException extends Exception{
    public SeatIsTakenException(String message) {
        super(message);
    }
}
