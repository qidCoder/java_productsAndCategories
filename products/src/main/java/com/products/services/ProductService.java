package com.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.products.models.Category;
import com.products.models.Product;
import com.products.repositories.ProductRepository;

@Service
public class ProductService {
	//Dependency Injection
	private ProductRepository productRepo;
	
	//constructor
	public ProductService(ProductRepository repo) {
		this.productRepo = repo;
	}
	

	


	//CRUD Methods
	//Get All products
	public List<Product> getAllProducts(){
		return this.productRepo.findAll();
	}
	
	//Get one product
	public Product getSingleProduct(Long id) {
		return this.productRepo.findById(id).orElse(null);
	}
	
	//Gets all products that are not categorized to a specific category
	public List<Product> findProductsUnassigned(Category category) {
		return this.productRepo.findByCategoriesNotContains(category);
	}

	
	//Create a product
	public Product createProduct(Product newProduct) {
		return this.productRepo.save(newProduct);
	}

	
	//Delete a product
	public void deleteProduct(Long id) {
		this.productRepo.deleteById(id);
	}
	
	//Update a product
	//Delete a product from a category - essentially updating the product to remove its category assignment
	public Product updateProduct(Product product) {
		return this.productRepo.save(product);
	}
	
	//Add new Category to a Product
	public void addCategoryToProduct(Product product, Category category) {
		// get Category list from the Product
		List<Category> categories = product.getCategories();
		// Add selected Category to Product
		categories.add(category);
		// Update DB
		this.productRepo.save(product);
	}
	

}
