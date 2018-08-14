package com.company.medprojectapp.repo;

import com.company.medprojectapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
