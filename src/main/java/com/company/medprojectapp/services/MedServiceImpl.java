package com.company.medprojectapp.services;

import com.company.medprojectapp.repo.MedRepository;
import com.company.medprojectapp.model.Medicine;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class MedServiceImpl implements MedService {

    private static final Logger log = Logger.getLogger( MedServiceImpl.class.getName() );

    private final MedRepository medRepository;

    public MedServiceImpl(MedRepository medRepository) {
        this.medRepository = medRepository;
    }

    @Override
    public Set<Medicine> getMedicines() {
        Set<Medicine> medSet = new HashSet<>();
        medRepository.findAll().iterator().forEachRemaining( medSet::add );
        log.info("LOGGER: added: " + medSet.toString() );
        return medSet;
    }

    @Override
    public Medicine findById(Long id) {
        Optional<Medicine> med = medRepository.findById( id );

         return med.get();
    }

    @Override
    public Medicine createMed(Medicine medicine) {
       Medicine m = medRepository.save( medicine );
       log.info("Created medicine: " + m.getId() );
        return m;
    }

    @Override
    public void deleteMed(Long id) {
        medRepository.deleteById( id );
    }

    @Override
    public void deleteMed(Medicine medicine) {
        medRepository.delete( medicine );
    }
}
