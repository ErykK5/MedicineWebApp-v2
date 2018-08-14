package com.company.medprojectapp.services;

import com.company.medprojectapp.model.Component;

import java.util.Set;

public interface ComponentService {

    Component findByMedicineIdAndComponentId(Long medicineId, Long componentId );

    Set<Component> allComponents();

    Component saveComponent( Component component );

    Component createComponent( Component component );

    void deleteById( Long medicineId, Long componentId );
}
