package com.tads4.tads4.dto;

public class PaymentFormDTO {

    private Integer orderId;

    public PaymentFormDTO(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
