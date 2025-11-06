package com.rytm.util;

public interface DTOValidator<T> {

    void validate(T toValidate);
}
