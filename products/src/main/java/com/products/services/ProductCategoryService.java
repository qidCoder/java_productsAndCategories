package com.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.products.models.ProductCategory;
import com.products.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	//Dependency Injection
	private ProductCategoryRepository productCategoryRepo;
	
	//constructor
	public ProductCategoryService(ProductCategoryRepository repo) {
		this.productCategoryRepo = repo;
	}
	
	//CRUD Methods
	// Creates a category
	public ProductCategory createProductCategory(ProductCategory newProductCategory) {
		return this.productCategoryRepo.save(newProductCategory);
	}
	
	// Retrieves all relationships between products and categories
		public List<ProductCategory> findAllProductCategories() {
			return this.productCategoryRepo.findAll();
		}
	
	// Creates a category
	public void removeProductCategory(Long id) {
		this.productCategoryRepo.deleteById(id);
	}

}
