package com.example;

/**
 * Request object for inserting a teaching.
 */
public class InsertTeachingRequest {
    private TeachingForm form;

    /**
     * Constructor for InsertTeachingRequest.
     * @param form the teaching form
     */
    public InsertTeachingRequest(TeachingForm form) {
        this.form = form;
    }

    /**
     * Gets the teaching form.
     * @return the form
     */
    public TeachingForm getForm() {
        return form;
    }
}