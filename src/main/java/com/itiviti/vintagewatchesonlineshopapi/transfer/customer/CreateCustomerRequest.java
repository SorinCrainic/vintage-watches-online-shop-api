package com.itiviti.vintagewatchesonlineshopapi.transfer.customer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateCustomerRequest {

    @NotNull
    private String customerFirstName;

    @NotNull
    private String customerLastName;

    @NotNull
    private String customerAddress;

    @NotNull
    @Min(0)
    private int customerAge;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerAge=" + customerAge +
                '}';
    }
}













