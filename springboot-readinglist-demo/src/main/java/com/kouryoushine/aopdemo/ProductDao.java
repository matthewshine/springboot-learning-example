package com.kouryoushine.aopdemo;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product,Long> {
}
