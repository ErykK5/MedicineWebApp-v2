package com.company.medprojectapp.repo;

import com.company.medprojectapp.model.Medicine;
import org.springframework.data.repository.CrudRepository;

public interface MedRepository extends CrudRepository<Medicine, Long> {
}
