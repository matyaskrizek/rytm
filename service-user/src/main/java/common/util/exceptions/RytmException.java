package common.util.exceptions;

import common.util.errors.ErrorDTO;
import common.util.errors.ErrorsDTO;
import common.util.errors.ErrorDetailDTO;
import org.springframework.http.HttpStatus;

import java.util.*;

public abstract class RytmException extends RuntimeException {
    protected List<ErrorDTO> errors;

    public RytmException(String message) {
        super(message);
    }

    public RytmException(String message, Throwable cause) {
        super(message, cause);
    }

    public RytmException(Throwable cause) {
        super(cause);
    }

    public RytmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void addError(ErrorDTO errorDTO){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(errorDTO);
    }

    public void addError(String param, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ErrorDTO(param, message));
    }

    public boolean hasErrors() {
        return Objects.nonNull(errors) && !errors.isEmpty();
    }

    public void throwIfErrors(){
        if(hasErrors()){
            throw this;
        }
    }

    public abstract HttpStatus getStatusCode();

    public abstract String toString();

    public List<ErrorDTO> getErrors() {
        if (Objects.isNull(errors)) {
            return Collections.emptyList();
        }
        return errors;
    }

    public ErrorsDTO getErrorsDTO() {
        ErrorsDTO errorsDTO = new ErrorsDTO();
        errorsDTO.setCode(getStatusCode().value());
        errorsDTO.setMessage(this.getMessage());

        Optional.ofNullable(this.getCause())
                .map(ErrorDetailDTO::new)
                .ifPresent(errorsDTO::setDetails);

        this.getErrors().forEach(errorsDTO::addError);

        return errorsDTO;
    }
}

