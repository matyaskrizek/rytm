package common.util.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailDTO {
    private String reason;
    @JsonIgnore // ignoring this field. This makes the response so big that it can't be read
    private Throwable exception;
    private String exception_string;

    public ErrorDetailDTO(Throwable exception) {
        this.exception = exception;
        if (exception != null) {
            this.reason = exception.getMessage();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            this.exception_string = sw.toString();
        }
    }
}