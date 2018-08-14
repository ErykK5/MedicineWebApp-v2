package com.company.medprojectapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String URL;

    @Lob
    private String dosage;

    @OneToOne(cascade = CascadeType.ALL)
    private Producer producer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicine")
    private Set<Component> components = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "med_category",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Medicine() {
    }

    public void addComponent( String name, Double amout ){
        Component component = new Component( name, amout );
        component.setMedicine( this );
        components.add( component );
    }

    public void addCategotry( Category category ){
        categories.add( category );
    }

    protected boolean canEqual(Object other) {
        return other instanceof Medicine;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.URL;
    }

    public String getDosage() {
        return this.dosage;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public Set<Component> getComponents() {
        return this.components;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
