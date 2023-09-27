package com.example.demo.models;

public class Address 
{
    public String shippingInstructions;
    public String name;
    public String street;
    public String country;
    public String postalCode;
    public String city;


    public String getShippingInstructions() {
        return shippingInstructions;
    }
    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}