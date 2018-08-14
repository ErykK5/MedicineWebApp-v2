package com.company.medprojectapp.services;

import com.company.medprojectapp.model.Producer;
import com.company.medprojectapp.repo.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Set<Producer> findAllProducrs() {
        Set<Producer> producersList = (Set<Producer>) producerRepository.findAll();
        return producersList;
    }
}
