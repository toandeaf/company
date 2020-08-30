package org.toandeaf.company.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpUtil {

    public static ResponseEntity convertToResponseEntity(Object content, HttpStatus status)
    {
        if(content == null)
        {
            return new ResponseEntity(null, null, HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(content, null, status);
        }
    }
}
