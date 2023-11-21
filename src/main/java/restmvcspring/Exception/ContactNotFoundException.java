package restmvcspring.Exception;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String message, Long id) {
        super(message);
    }
}

