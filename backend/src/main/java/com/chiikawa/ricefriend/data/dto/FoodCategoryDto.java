package com.chiikawa.ricefriend.data.dto;

import com.chiikawa.ricefriend.data.entity.FoodCategory;
import com.chiikawa.ricefriend.data.entity.MenuCategory;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class FoodCategoryDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class FoodCategoryResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private MenuCategory menu;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        public FoodCategoryResponseDto(FoodCategory food) {
            this.id = food.getId();
            this.menu = food.getMenu();
            this.name = food.getName();
        }
    }
}