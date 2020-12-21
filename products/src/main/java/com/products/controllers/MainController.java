package com.products.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.products.models.Category;
import com.products.models.Product;
import com.products.models.ProductCategory;
import com.products.services.CategoryService;
import com.products.services.ProductCategoryService;
import com.products.services.ProductService;

@Controller
public class MainController {
	//dependency injection
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	//ROUTES
	@GetMapping("/")
	public String index() {
		return "redirect:/dashboard";
	}
	
	//DISPLAYS
	//display dashboard
	@GetMapping("/dashboard")
	public String dashboard(Model viewModel) {//need model to display to frontend from DB
		List<Product> allProducts = this.productService.getAllProducts();//store everything in a list
		viewModel.addAttribute("allProducts", allProducts);
		
		List<Category> allCategorys = this.categoryService.getAllCategorys();//store everything in a list
		viewModel.addAttribute("allCategorys", allCategorys);
		
		return "dashboard.jsp";
	}

	//display add a new product
	@GetMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {//need model to display to frontend from DB
		
		return "addProduct.jsp";
	}


	//display create a category form
	//	Have a method handler in the controller for the following example url: /categorys/create?name=Manza. 
	@GetMapping("/categorys/create")
	public String newCategory(@ModelAttribute("category") Category category) {
		
		return "addCategory.jsp";
	}

	//display specific category
	@GetMapping("/categorys/{id}")
	public String showCategory(Model viewModel, @PathVariable("id") Long id, @ModelAttribute("productCategory") ProductCategory productCategory) {//need model to display to frontend from DB


		Category showCategory = this.categoryService.getSingleCategory(id);
		viewModel.addAttribute("category", showCategory);
		
		//in order to grab the list of Products:	
		List<Product> allProducts = showCategory.getProducts();//store everything in a list
		List<Product> notAllProducts = this.productService.findProductsUnassigned(showCategory);
		viewModel.addAttribute("allProducts", allProducts);
		viewModel.addAttribute("notAllProducts", notAllProducts);
		
		return "showCategory.jsp";
	}
	
	//display specific product
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, @ModelAttribute("productCategory") ProductCategory productCategory, Model viewModel) {//need model to display to frontend from DB


		Product showProduct = this.productService.getSingleProduct(id);
		viewModel.addAttribute("product", showProduct);
		
		//in order to grab the list of Products:	
		List<Category> allCategorys = showProduct.getCategories();
		List<Category> notAllCategorys = categoryService.findCategoriesUnassigned(showProduct);
		viewModel.addAttribute("allCategorys", allCategorys);
		viewModel.addAttribute("categoriesNotListed", notAllCategorys);
		
		return "showProduct.jsp";
	}
	

	///////////////////////////////////////////////////////////////////

	//POST MAPPING	
	//Create new product
	//Have a method handler in the controller for the following example url: /products/create?productName=John&lastName=Doe&price=35
	@PostMapping("/products/create")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
	     if(result.hasErrors()) {
	         return "addProduct.jsp";//re-render the price if there are errors
	     }    
	     
	    this.productService.createProduct(product);
	    
	    return "redirect:/dashboard";
	}
	


	//create a new category
	@PostMapping("/categorys/create")
	public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "addCategory.jsp";
		}
		else {
			categoryService.createCategory(category);
			return "redirect:/dashboard";
		}
	}

	//add category to product
		@PostMapping("/products/addCategory")
	public String addCategoryToProduct(@ModelAttribute("productCategory") ProductCategory productCategory, BindingResult result) {
		if(result.hasErrors()) {
			return "showProduct.jsp";
		}
		else {
			productCategoryService.createProductCategory(productCategory);
//			Long id = productCategory.getProduct().getId();
			return "redirect:/dashboard";
			// return "redirect:/products/"+id;
		}  
	}

		// Removing a category from a product
//	@RequestMapping(value="/products/removeCategory", method=RequestMethod.POST)
//	public String removeCategoryFromProduct(
//			@RequestParam(value="product") Product product,
//			@RequestParam(value="category") Category category) {
//		
//		Long idProductCategoryToBeRemoved;
//		List <ProductCategory> allProductsCategories = productCategoryService.findAllProductCategories();
//		
//		
//		for (ProductCategory eachAssociation : allProductsCategories) {
//			if(eachAssociation.getProduct().getId()==product.getId() &&
//				eachAssociation.getCategory().getId()==category.getId()) {
//				
//				idProductCategoryToBeRemoved = eachAssociation.getId();
//				productCategoryService.removeProductCategory(idProductCategoryToBeRemoved);
//			}
//		}
//		
//		Long id = product.getId();
//		return "redirect:/products/"+id;
//		
//	}

		
		//add product to category
//		@PostMapping("/products/{product_id}/add")
//	public String addProductToCategoryt(@PathVariable("product_id") Long product_id, @RequestParam(value="category", required=false) Long category_id) {
//		Category category = this.categoryService.getSingleCategory(category_id);
//		Product product = this.productService.getSingleProduct(product_id); 
//		
//		//update product list for specific product
//		List<Product> products = category.getProduct();
//		products.add(product);
//		// Update The DB
//		this.categoryService.updateCategory(category);
//	    
//	    return "redirect:/dashboard";
//	}
	


	///////////////////////////////////////////////////////////////////
	//DELETIONS
	//delete entire product
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		this.productService.deleteProduct(id);
		
		return "redirect:/dashboard";
	}

	//delete entire category - note only works if category has no products assigned to it
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		this.categoryService.deleteCategory(id);
		
		return "redirect:/dashboard";
	}
	
	///////////////////////////////////////////////////////////////////

	//UPDATES
	//remove category from product
	//	Have a method handler in the controller for the following example url: /categorys/3/remove?product=1. This method should remove product with id 1 from the category with id 3. Remove multiple products from a single categoryitory.
//	@GetMapping("/categorys/{category_id}/remove")
//	public String removeProductromCategory(@PathVariable("category_id") Long category_id, @RequestParam(value="product", required=false) Long product_id) {
//		Product product = this.productService.getSingleProduct(product_id);
////		Category category = this.categoryService.getSingleCategory(category_id); //not used
//		
//		product.setCategory(null);
//	     
//	    this.productService.updateProduct(product);
//	    
//	    return "redirect:/dashboard";
//	}
	
	// POST Request for adding product to a category
//	@RequestMapping(value="/categories/addProduct", method=RequestMethod.POST)
//	public String addProductToCategory(@ModelAttribute("productCategory") ProductCategory productCategory, BindingResult result) {
//		
//		if(result.hasErrors()) {
//			return "showCategory.jsp";
//		}
//		else {
//			productCategoryService.createProductCategory(productCategory);
//			Long id = productCategory.getProduct().getId();
//			return "redirect:/categories/"+id;
//		}
//				
//	}
	
	// POST Request for removing a category from a product
//	@RequestMapping(value="/categories/removeProduct", method=RequestMethod.POST)
//	public String removeProductFromCategory(
//			@RequestParam(value="product") Product product,
//			@RequestParam(value="category") Category category) {
//		
//		Long idProductCategoryToBeRemoved;
//		List <ProductCategory> allProductsCategories = productCategoryService.findAllProductCategories();
//		
//		
//		for (ProductCategory eachAssociation : allProductsCategories) {
//			if(eachAssociation.getProduct().getId()==product.getId() &&
//				eachAssociation.getCategory().getId()==category.getId()) {
//				
//				idProductCategoryToBeRemoved = eachAssociation.getId();
//				productCategoryService.removeProductCategory(idProductCategoryToBeRemoved);
//			}
//		}
//		
//		Long id = category.getId();
//		return "redirect:/categories/"+id;
//		
//	}

}
