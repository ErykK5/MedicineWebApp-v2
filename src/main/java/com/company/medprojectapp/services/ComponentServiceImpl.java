package com.company.medprojectapp.services;

import com.company.medprojectapp.model.Component;
import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.repo.ComponentRepository;
import com.company.medprojectapp.repo.MedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class ComponentServiceImpl implements ComponentService {

    private static final Logger log = Logger.getLogger( ComponentServiceImpl.class.getName() );

    private final ComponentRepository componentRepository;

    private final MedRepository medRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository, MedRepository medRepository) {
        this.componentRepository = componentRepository;
        this.medRepository = medRepository;
    }

    @Override
    public Component findByMedicineIdAndComponentId(Long medicineId, Long componentId ) {
        Optional<Medicine> medicineOptional = medRepository.findById( medicineId );
        Medicine medicine = medicineOptional.get();
        Optional<Component> c = medicine.getComponents().stream().filter( com -> com.getId().equals( componentId ) ).findFirst();
        log.info( "Found medicine with: " + c.get().getId() + " " + c.get().getComponentName() );
        componentRepository.save( c.get() );
        medRepository.save( medicineOptional.get() );
        return c.get();
    }

    @Override
    public Set<Component> allComponents() {
        Set<Component> components = new HashSet<>();
        componentRepository.findAll().iterator().forEachRemaining( components::add );
        return components;
    }

    @Override
    @Transactional
    public Component saveComponent(Component component) {
        Optional<Medicine> medicine = medRepository.findById(component.getMedicine().getId());

        if (!medicine.isPresent()) {
            log.info("MEDICINE NOT FOUND");
            return new Component();
        } else {
            Medicine m = medicine.get();
            Optional<Component> c = m.getComponents().stream().filter(com -> com.getId().equals(component.getId())).findFirst();
            if (c.isPresent()) {
                Component component1 = c.get();
                component1.setComponentName(component.getComponentName());
                component1.setAmount(component.getAmount());
                component1.setMedicine(component.getMedicine());
                log.info(" c is present");
            } else {
                m.addComponent(component.getComponentName(), component.getAmount());
                log.info(" c is NOT present");
            }

            Medicine medicine1 = medRepository.save(m);
            Optional<Component>  c2 = medicine1.getComponents().stream().filter(component2 -> component2.getId().equals(component.getId())).findFirst();
            log.info("[saveComponent] c2: " + c2.get().getComponentName() );
            return c2.get();

        }
    }

    @Override
    @Transactional
    public Component createComponent( Component component ) {

        Component c = componentRepository.save( component );
        log.info("createComponent: Created component: " + c.getComponentName() + " with id: " + c.getId() );
        return c;
    }

    @Override
    public void deleteById(Long medicineId, Long componentId) {

        Optional<Medicine> optMedicine = medRepository.findById( medicineId );

        if(optMedicine.isPresent()){
            Medicine m = optMedicine.get();
            Optional<Component> optComponent = m.getComponents().stream().filter( com -> com.getId().equals( componentId)).findFirst();

            if( optComponent.isPresent() ){
                Component comToDelete = optComponent.get();
                comToDelete.setMedicine( null );
                m.getComponents().remove( optComponent.get() );
                medRepository.save( m );
            }
        } else {
            log.info( "Error: Component not found");
        }
    }
}
