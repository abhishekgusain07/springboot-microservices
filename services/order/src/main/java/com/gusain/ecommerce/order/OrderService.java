package com.gusain.ecommerce.order;

import com.gusain.ecommerce.customer.CustomerClient;
import com.gusain.ecommerce.customer.CustomerResponse;
import com.gusain.ecommerce.exception.BusinessException;
import com.gusain.ecommerce.kafka.OrderConfirmation;
import com.gusain.ecommerce.kafka.OrderProducer;
import com.gusain.ecommerce.orderline.OrderLineRequest;
import com.gusain.ecommerce.orderline.OrderLineService;
import com.gusain.ecommerce.payment.PaymentClient;
import com.gusain.ecommerce.payment.PaymentRequest;
import com.gusain.ecommerce.product.ProductClient;
import com.gusain.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final Mapper mapper;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        //check the customer / using openFeign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found with Id:: "+request.customerId()));

        //purchase the product --> using product microservice(using restTemplate)
        var purchasedProduct = this.productClient.purchaseProduct(request.products());

        //persist order object
        var order = this.repository.save(mapper.toOrder(request));

        //persist orderlines
        for(PurchaseRequest purchaseRequest : request.products()) {
            this.orderLineService.saveOrderline(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // start payment processing -> using payment microservice

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);


        //send the order confirmation --> notification microservice
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order not found with Id:: %s",orderId)));
    }
}
