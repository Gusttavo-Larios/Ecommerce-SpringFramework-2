package com.tads4.tads4.dto;

import java.util.List;

public class OrderFormDTO {

    private OrderDTO data;
    private Long[] idItems;

    public OrderFormDTO(OrderDTO data, Long[] idItems) {
        this.data = data;
        this.idItems = idItems;
    }

    public OrderDTO getData() {
        return data;
    }

    public void setData(OrderDTO data) {
        this.data = data;
    }

    public Long[] getIdItems() {
        return idItems;
    }

    public void setIdItems(Long[] idItems) {
        this.idItems = idItems;
    }
}
