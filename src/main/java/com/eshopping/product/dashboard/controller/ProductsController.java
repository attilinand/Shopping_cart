package com.eshopping.product.dashboard.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.product.dashboard.comparator.AvailabilityComparator;
import com.eshopping.product.dashboard.comparator.DiscountPercentageComparator;
import com.eshopping.product.dashboard.model.Product;
import com.eshopping.product.dashboard.repository.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
		
	@Autowired
    private ProductRepository productRepository;

	/**
	 * Service method which takes product request, save and return response
	 *
	 * @param product
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {

		if (productRepository.existsById(product.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		productRepository.save(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Service method which takes product request, update by id and return response
	 *
	 * @param product
	 *
	 * @param product_id
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@PutMapping(value = "/{product_id}")
	public ResponseEntity<?> updateProductById(@Valid @RequestBody Product product, @PathVariable long product_id) {

		if (!productRepository.findById(product_id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<Product> originalProductOptional = productRepository.findById(product_id);
		Product originalProduct = originalProductOptional.get();
		originalProduct.setRetailPrice(product.getRetailPrice());
		originalProduct.setDiscountedPrice(product.getDiscountedPrice());
		originalProduct.setAvailability(product.getAvailability());
		productRepository.save(originalProduct);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Service method which takes product_id and return product details in response
	 *
	 * @param product_id
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@GetMapping(value = "/{product_id}")
	public ResponseEntity<?> returnProductById(@PathVariable long product_id) {

		if (!productRepository.existsById(product_id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Optional<Product> originalProductOptional = productRepository.findById(product_id);
		Product originalProduct = originalProductOptional.get();
		return new ResponseEntity<Product>(originalProduct, HttpStatus.OK);
	}

	/**
	 * Service method which fetches product list based on category, sort and return
	 * product list
	 *
	 * @param category
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@GetMapping(params = { "category" })
	public ResponseEntity<?> returnProductByCategory(@RequestParam("category") String category) {

		List<Product> products = productRepository.getAllProductsForCategory(category);
		if (products == null || products.isEmpty()) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
		Collections.sort(products, new AvailabilityComparator().thenComparingDouble(Product::getDiscountedPrice)
				.thenComparingLong(Product::getId));

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/**
	 * Service method which fetches product list based on category,availability,
	 * sort and return product list
	 *
	 * @param category
	 *
	 * @param availability
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@GetMapping(params = { "category", "availability" })
	public ResponseEntity<?> returnProductByCategoryAndAvailability(@RequestParam("category") String category,
			@RequestParam("availability") Boolean availability) {
		String categoryNew = category.replaceAll("%20", " ");
		List<Product> products = productRepository.getAllProductsForCategoryAndAvailability(categoryNew, availability);
		if (products == null || products.isEmpty()) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		}

		Collections.sort(products, new DiscountPercentageComparator().thenComparingDouble(Product::getDiscountedPrice)
				.thenComparingLong(Product::getId));

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/**
	 * Service method which returns all products.
	 *
	 * @return ResponseEntity - HTTP response
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public ResponseEntity<?> returnAllProducts() {
		List<Product> products = productRepository.findAll();
		Collections.sort(products, Comparator.comparing(Product::getId));
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
