package org.backend.dto.responseDto;

import java.util.List;

public class GeneralResponse<T>{
    T body;
    List<String> errors;

    public GeneralResponse(T body, List<String> errors) {
        this.body = body;
        this.errors = errors;
    }

    public T getBody() {
        return body;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error){
        errors.add(error);
    }

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "body=" + body +
                ", errors=" + errors +
                '}';
    }
}
