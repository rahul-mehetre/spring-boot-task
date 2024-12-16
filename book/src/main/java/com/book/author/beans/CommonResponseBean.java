package com.book.author.beans;

import lombok.Data;

@Data
public class CommonResponseBean {

    private Boolean status;
    private String message;
    private Object responseBean;

}
