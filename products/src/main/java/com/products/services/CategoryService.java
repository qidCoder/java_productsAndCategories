package com.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.products.models.Category;
import com.products.models.Product;
import com.products.repositories.CategoryRepository;

@Service
public class CategoryService {
	//Dependency Injection
	private CategoryRepository categoryRepo;
	
	//constructor
	public CategoryService(CategoryRepository repo) {
		this.categoryRepo = repo;
	}
	
	//CRUD Methods
	//Get All categorys
	public List<Category> getAllCategorys(){
		return this.categoryRepo.findAll();
	}
	
	//Get one category
	public Category getSingleCategory(Long id) {
		return this.categoryRepo.findById(id).orElse(null);
	}

	//Create a category
	public Category createCategory(Category newCategory) {

		return this.categoryRepo.save(newCategory);
	}
	
	//Delete a category
	//NOTE: this only deletes the category if it has no products assigned to it!
	public void deleteCategory(Long id) {
		this.categoryRepo.deleteById(id);
	}
	
	//Update a category
	//Delete a category from a category - essentially updating the category to remove its category assignment
	public Category updateCategory(Category category) {
		return this.categoryRepo.save(category);
	}
	
	//Get all categories that a specific product has not been categorized yet
	public List<Category> findCategoriesUnassigned(Product product) {
		return this.categoryRepo.findByProductsNotContains(product);
	}
	
	// Add new Product to a Category
	public void addProductToCategory(Category category, Product product) {
		// get Product list from the Category
		List<Product> products = category.getProducts();
		// Add selected Category to Product
		products.add(product);
		// Update DB
		this.categoryRepo.save(category);
	}
	

	
	


}
