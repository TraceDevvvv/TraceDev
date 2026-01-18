package com.etour.dto;

/**
 * Generic response container that can hold either data or an error.
 * @param <T> the type of data.
 */
public class Response<T> {
    private T data;
    private ErrorMessageDTO error;

    public Response(T data, ErrorMessageDTO error) {
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorMessageDTO getError() {
        return error;
    }

    public void setError(ErrorMessageDTO error) {
        this.error = error;
    }

    /**
     * Determines if the response is successful (i.e., contains data and no error).
     * @return true if successful, false otherwise.
     */
    public boolean isSuccess() {
        return error == null;
    }
}