package com.products.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.products.models.ProductCategory;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long>{
	//SELECT * FROM DLs
	List<ProductCategory> findAll();
}
