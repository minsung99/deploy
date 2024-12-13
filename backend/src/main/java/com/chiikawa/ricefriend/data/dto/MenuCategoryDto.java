package com.chiikawa.ricefriend.data.dto;

import com.chiikawa.ricefriend.data.entity.MenuCategory;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class MenuCategoryDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MenuCategoryResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        public MenuCategoryResponseDto(MenuCategory menu) {
            this.id = menu.getId();
            this.name = menu.getName();
        }
    }
}