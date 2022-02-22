package se.lexicon.recipeassignmentapi.model.dto;

import java.util.List;
import java.util.Map;

public class ValidationErrorResponseDto extends ExceptionResponseDto{
    private Map<String, List<String>> validationErrors;

    public ValidationErrorResponseDto() {
    }

    public Map<String, List<String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
