package com.mysite.mm.store;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByNameAndLatAndLng(String name, Double lat, Double lng);
    List<Store> findAll(Specification<Store> spec);
}
