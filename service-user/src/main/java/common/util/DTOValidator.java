package common.util;

public interface DTOValidator<T> {

    void validate(T toValidate);
}
