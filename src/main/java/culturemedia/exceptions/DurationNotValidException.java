package culturemedia.exceptions;

public class DurationNotValidException extends RuntimeException {
    public DurationNotValidException(String title, Double duration) {
        super(title + duration);
    }
}
