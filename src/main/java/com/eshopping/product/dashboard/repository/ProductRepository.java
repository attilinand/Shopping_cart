package com.eshopping.product.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eshopping.product.dashboard.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 

/**
* this method returns list  of products  based on category
*
* @param category - product category
* @return list of products
*
*/
    @Query("SELECT p FROM Product p where p.category= :category")
public List<Product> getAllProductsForCategory(@Param("category") String category);
   
   
    /**
* this method returns list  of products  based on category and availability
*
* @param category - product category
* @param availability - product availability
* @return list of products
*
*/
    @Query("SELECT p FROM Product p where p.category= :category and p.availability= :availability")
public List<Product> getAllProductsForCategoryAndAvailability(@Param("category") String category,@Param("availability") Boolean availability );
	
}
