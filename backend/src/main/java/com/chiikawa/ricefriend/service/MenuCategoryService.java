package com.chiikawa.ricefriend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.chiikawa.ricefriend.data.dto.MenuCategoryDto;
import com.chiikawa.ricefriend.data.entity.MenuCategory;
import com.chiikawa.ricefriend.data.repository.MenuCategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MenuCategoryService {
    @Autowired
    private MenuCategoryRepository menuRepository;

    public List<MenuCategoryDto.MenuCategoryResponseDto> getAllMenus() {
        List<MenuCategory> menus = menuRepository.findAll();

        return menus.stream()
                .map(MenuCategoryDto.MenuCategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    public MenuCategoryDto.MenuCategoryResponseDto getMenuById(int id) {
        MenuCategory entity = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. id=" + id));

        return new MenuCategoryDto.MenuCategoryResponseDto(entity);
    }
}