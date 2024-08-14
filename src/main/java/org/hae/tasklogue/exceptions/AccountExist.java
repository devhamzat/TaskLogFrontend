package org.hae.tasklogue.exceptions;

public class AccountExist extends RuntimeException {
    public AccountExist(String error) {
        super(error);
    }
}
