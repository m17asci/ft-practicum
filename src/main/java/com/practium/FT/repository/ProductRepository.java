package com.practium.FT.repository;

import com.practium.FT.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
  List<Product> findByProductExpirationDateAfterOrProductExpirationDateIsNull(LocalDate date);
  List<Product> findByProductExpirationDateBefore(LocalDate exp);
}
