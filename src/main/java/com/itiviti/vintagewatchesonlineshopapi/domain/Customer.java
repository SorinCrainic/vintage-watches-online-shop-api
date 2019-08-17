package com.itiviti.vintagewatchesonlineshopapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

    @Id
    @NotNull
    @GeneratedValue
    private long id;

    @NotNull
    private String customerFirstName;

    @NotNull
    private String customerLastName;

    @NotNull
    private String customerAddress;

    @NotNull
    @Min(0)
    private int customerAge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        return "Customer{" +
                "id=" + id +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerAdress='" + customerAddress + '\'' +
                ", customerAge=" + customerAge +
                '}';
    }
}
