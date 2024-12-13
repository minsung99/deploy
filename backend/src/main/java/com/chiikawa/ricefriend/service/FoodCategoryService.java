package com.chiikawa.ricefriend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.chiikawa.ricefriend.data.dto.FoodCategoryDto;
import com.chiikawa.ricefriend.data.entity.FoodCategory;
import com.chiikawa.ricefriend.data.repository.FoodCategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FoodCategoryService {
    @Autowired
    private FoodCategoryRepository foodRepository;

    public List<FoodCategoryDto.FoodCategoryResponseDto> getAllFood() {
        List<FoodCategory> food = foodRepository.findAll();

        return food.stream()
                .map(FoodCategoryDto.FoodCategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    public FoodCategoryDto.FoodCategoryResponseDto getFoodById(int id) {
        FoodCategory entity = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 음식이 없습니다. id=" + id));

        return new FoodCategoryDto.FoodCategoryResponseDto(entity);
    }
}