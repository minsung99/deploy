package com.chiikawa.ricefriend.data.repository;

import com.chiikawa.ricefriend.data.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
}