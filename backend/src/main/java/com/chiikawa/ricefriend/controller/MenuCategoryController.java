package com.chiikawa.ricefriend.controller;

import java.util.List;

import com.chiikawa.ricefriend.data.dto.MenuCategoryDto;
import com.chiikawa.ricefriend.service.MenuCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuCategoryController {

    private final MenuCategoryService menuService;

    // 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<MenuCategoryDto.MenuCategoryResponseDto> getMenuById(@PathVariable int id) {
        MenuCategoryDto.MenuCategoryResponseDto responseDto = menuService.getMenuById(id);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuCategoryDto.MenuCategoryResponseDto>> getMenuList() {
        List<MenuCategoryDto.MenuCategoryResponseDto> menuList = menuService.getAllMenus();

        return ResponseEntity.ok(menuList);
    }
}