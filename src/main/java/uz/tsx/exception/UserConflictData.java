package uz.tsx.exception;

public class UserConflictData extends RuntimeException{
    public UserConflictData(String message) {
        super(message);
    }
}
