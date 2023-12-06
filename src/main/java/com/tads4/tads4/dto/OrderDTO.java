package com.tads4.tads4.dto;

import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.User;

import java.time.Instant;

public class OrderDTO {
        private Long id;
        private String status;
        private Long client;
        private Instant moment;

    public OrderDTO(Long id, String status, Long client, Instant moment) {
        this.id = id;
        this.status = status;
        this.client = client;
        this.moment = moment;
    }

    public OrderDTO(Order entity) {
        User client = entity.getClient();
        this.id = entity.getId();
        this.status = String.valueOf(entity.getSatus());
        this.client = client.getId();
        this.moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }
}
