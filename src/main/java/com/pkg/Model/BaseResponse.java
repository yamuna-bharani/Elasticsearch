package com.pkg.Model;

import lombok.Data;

/**
 * Created by via on 7/18/18.
 */
@Data
public class BaseResponse {
    boolean success;
    String message;
    Error error;
    String data;
}
