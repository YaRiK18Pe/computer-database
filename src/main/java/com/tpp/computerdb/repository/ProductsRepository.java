package com.tpp.computerdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.computerdb.models.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
