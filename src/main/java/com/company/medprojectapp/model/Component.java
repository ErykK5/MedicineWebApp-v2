package com.company.medprojectapp.model;

import javax.persistence.*;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String componentName;
    private Double amount;

    @ManyToOne
    private Medicine medicine;

    public Component(String componentName, Double amount, Medicine medicine) {
        this.componentName = componentName;
        this.amount = amount;
        this.medicine = medicine;
    }

    public Component(String componentName, Double amount) {
        this.componentName = componentName;
        this.amount = amount;
    }

    public  Component( Component component ){
        this.amount = component.amount;
        this.componentName = component.componentName;
        this.medicine = component.medicine;
        this.id = component.id;
    }

    public Component() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof Component;
    }

    public Long getId() {
        return this.id;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Medicine getMedicine() {
        return this.medicine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
