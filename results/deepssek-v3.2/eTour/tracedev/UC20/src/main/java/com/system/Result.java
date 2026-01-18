package com.system;

import java.util.List;

/**
 * Represents the result of an operation.
 */
public class Result {
    private boolean success;
    private Object data;
    private List<String> errors;

    public Result(boolean success, Object data, List<String> errors) {
        this.success = success;
        this.data = data;
        this.errors = errors;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}