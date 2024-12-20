package com.tpp.computerdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.computerdb.models.Component;

@Repository
public interface ComponentsRepository extends JpaRepository<Component, Integer> {
}
