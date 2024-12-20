package com.tpp.computerdb.repository;

import com.tpp.computerdb.models.ProductComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductComponentsRepository extends JpaRepository<ProductComponent, Integer> {
    Optional<ProductComponent> findByProduct_ProductIdAndComponent_ComponentId(Integer productId, Integer componentId);
    void deleteByProduct_ProductIdAndComponent_ComponentId(Integer productId, Integer componentId);
}
