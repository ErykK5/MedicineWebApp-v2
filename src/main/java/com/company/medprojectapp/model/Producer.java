package com.company.medprojectapp.model;

import javax.persistence.*;

@Entity
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String producerName;

    @OneToOne
    private Medicine medicine;

    public Producer() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof Producer;
    }

    public Long getId() {
        return this.id;
    }

    public String getProducerName() {
        return this.producerName;
    }

    public Medicine getMedicine() {
        return this.medicine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
