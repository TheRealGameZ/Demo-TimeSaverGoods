package com.example.demo.models;

import com.example.demo.models.Address;
import com.example.demo.models.PaymentMethod;

public class CombinedModel {
    private Address address;
    private PaymentMethod paymentMethod;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}