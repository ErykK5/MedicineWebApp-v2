package com.company.medprojectapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    Set<Medicine> medicines = new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public Category(String categoryName, Set<Medicine> medicines) {
        this.categoryName = categoryName;
        this.medicines = medicines;
    }

    public void addMedicine(Medicine medicine){
        medicines.add( medicine );
    }

    protected boolean canEqual(Object other) {
        return other instanceof Category;
    }

    public Long getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Set<Medicine> getMedicines() {
        return this.medicines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}
