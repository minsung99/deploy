package com.chiikawa.ricefriend.controller;

import java.util.List;

import com.chiikawa.ricefriend.data.dto.FoodCategoryDto;
import com.chiikawa.ricefriend.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodCategoryController {

    private final FoodCategoryService foodService;

    // 음식 조회
    @GetMapping("/{id}")
    public ResponseEntity<FoodCategoryDto.FoodCategoryResponseDto> getFoodById(@PathVariable int id) {
        FoodCategoryDto.FoodCategoryResponseDto responseDto = foodService.getFoodById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 음식 조회
    @GetMapping
    public ResponseEntity<List<FoodCategoryDto.FoodCategoryResponseDto>> getFoodList() {
        List<FoodCategoryDto.FoodCategoryResponseDto> foodList = foodService.getAllFood();

        return ResponseEntity.ok(foodList);
    }
}