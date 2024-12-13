package com.chiikawa.ricefriend.data.repository;

import com.chiikawa.ricefriend.data.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {
}