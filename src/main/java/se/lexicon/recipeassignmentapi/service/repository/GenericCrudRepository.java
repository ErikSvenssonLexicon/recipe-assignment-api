package se.lexicon.recipeassignmentapi.service.repository;

public interface GenericCrudRepository<T, ID, DTO> {
    T create(DTO form);
    T findById(ID id);
    T update(ID id, DTO form);
    boolean delete(ID id);
}
