package se.lexicon.recipeassignmentapi.service.repository;

import se.lexicon.recipeassignmentapi.exception.AppResourceNotFoundException;

import java.util.function.Supplier;

public interface GenericCrudRepository<T, ID, DTO> {
    T create(DTO form);
    T findById(ID id);
    T update(ID id, DTO form);
    boolean delete(ID id);
    default  Supplier<AppResourceNotFoundException> getException(String message){
        return () -> new AppResourceNotFoundException(message);
    }
}
