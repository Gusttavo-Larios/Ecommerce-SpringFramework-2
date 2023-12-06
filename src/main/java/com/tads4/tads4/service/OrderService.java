package com.tads4.tads4.service;

import com.tads4.tads4.dto.OrderDTO;
import com.tads4.tads4.dto.OrderFormDTO;
import com.tads4.tads4.entities.*;
import com.tads4.tads4.repositories.OrderItemRepository;
import com.tads4.tads4.repositories.OrderRepository;
import com.tads4.tads4.repositories.ProductRepository;
import com.tads4.tads4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository repositoryItem;

    @Autowired
    private ProductRepository repositoryProduct;

    @Autowired
    private UserRepository repositoryUser;

    @Transactional
    public OrderDTO insert(OrderFormDTO dto) {
        Order entity = new Order();
        copyDtoToEntity(dto.getData(), entity);
        entity =repository.save(entity);

        for (int i = 0; i < dto.getIdItems().length; i++) {
            OrderItem orderItem = new OrderItem();

            Long[] productsIds = dto.getIdItems();

            // code block to be executed
            Optional<Product> product = repositoryProduct.findById(productsIds[i]);

            if(product.isPresent()) {
                orderItem.setOrder(entity);
                orderItem.setPrice(product.get().getPrice());
                orderItem.setQuantity(1);
                orderItem.setProduct(product.get());
                repositoryItem.save(orderItem);
            }
        }

        return new OrderDTO(entity);
    }

    @Transactional
    private void copyDtoToEntity(OrderDTO dto, Order entity) {
        entity.setId(dto.getId());

        User user = repositoryUser.getReferenceById(dto.getClient());

        entity.setClient(user);

        entity.setMoment(Instant.now());
        entity.setSatus(OrderStatus.valueOf("WAITING_PAYMENT"));
    }

    public OrderDTO payment(Long orderId) {
        Order order = repository.getReferenceById(orderId);
        order.setSatus(OrderStatus.valueOf("PAID"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMoment(Instant.now());
        order.setPayment(payment);

        order = repository.save(order);

        return new OrderDTO(order);
    }

}