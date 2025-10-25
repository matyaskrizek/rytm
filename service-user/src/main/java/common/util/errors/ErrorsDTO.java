package common.util.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsDTO {
    private String message;
    private int code;

    private ErrorDetailDTO details;
    private List<ErrorDTO> errors;

    public ErrorsDTO(Throwable ex) {
        addException(ex);
    }

    public ErrorsDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public void addError(String param, String message){
        addError(new ErrorDTO(param, message));
    }

    public void addError(ErrorDTO errorDTO) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(errorDTO);
    }

    public void addErrors(ErrorsDTO errorsDTO){
        if(errors == null){
            errors = new ArrayList<>();
        }
        if(errorsDTO.isError()) {
            if(!this.isError()) {
                message = errorsDTO.message;
                code = errorsDTO.code;
                details = errorsDTO.details;
            }
            errors.addAll(errorsDTO.getErrors());
        }
    }

    public void addException(Throwable ex) {
        details = new ErrorDetailDTO(ex);
    }

    public boolean isError(){
        return (code != 0 && message != null) || (errors != null && !errors.isEmpty()) || details != null;
    }

    public boolean hasParamErrors(){
        return errors != null && !errors.isEmpty();
    }
}