package uz.tsx.validation;

public class Validation {
    public static boolean checkId(Long id) {
        return id != null && id > 0;
    }
}
