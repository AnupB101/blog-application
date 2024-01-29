package com.anup.blog.exception;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
public class ApiResponse{
    private Date timestamp;
    private HttpStatus status;
    private String message;



}
