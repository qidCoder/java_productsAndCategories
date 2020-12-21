package com.products.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.products.models.Category;
import com.products.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	//SELECT * FROM DLs
	List<Product> findAll();
	
    //retrieves all products that are not assigned to a specific category
	List<Product> findByCategoriesNotContains(Category category);
}
