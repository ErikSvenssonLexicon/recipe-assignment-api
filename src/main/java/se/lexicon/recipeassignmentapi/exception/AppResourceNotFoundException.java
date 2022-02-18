package se.lexicon.recipeassignmentapi.exception;

public class AppResourceNotFoundException extends RuntimeException{
    public AppResourceNotFoundException(String message) {
        super(message);
    }
}
