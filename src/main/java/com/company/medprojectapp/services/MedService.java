package com.company.medprojectapp.services;

import com.company.medprojectapp.model.Medicine;

import java.util.Set;

public interface MedService {
    Set<Medicine> getMedicines();

    Medicine findById(Long id );

    Medicine createMed( Medicine medicine );

    void deleteMed( Long id );

    void deleteMed( Medicine medicine );
}
