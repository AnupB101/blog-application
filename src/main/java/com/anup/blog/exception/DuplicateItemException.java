package com.anup.blog.exception;

import lombok.Data;

@Data
public class DuplicateItemException extends RuntimeException{
    private String message;

    public DuplicateItemException(String message) {
        super(message);
        this.message = message;
    }
}
