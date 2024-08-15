package org.hae.tasklogue.exceptions;

public class EmptyRequiredFields extends RuntimeException{


    public EmptyRequiredFields(String error) {
        super(error);
    }
}
