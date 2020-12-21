package com.products.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.products.models.Category;
import com.products.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	//SELECT * FROM DLs
	List<Category> findAll();
	
    //retrieves all categories that a specific product has not been assigned to yet
	List<Category> findByProductsNotContains(Product product);
}
