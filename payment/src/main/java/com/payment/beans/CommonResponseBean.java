package com.payment.beans;

import lombok.Data;

@Data
public class CommonResponseBean {

    private Boolean status;
    private String message;
    private Object responseBean;

}
