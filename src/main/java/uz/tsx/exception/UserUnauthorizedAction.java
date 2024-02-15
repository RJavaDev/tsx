package uz.tsx.exception;

public class UserUnauthorizedAction extends RuntimeException{
    public UserUnauthorizedAction(String message) {
        super(message);
    }
}
