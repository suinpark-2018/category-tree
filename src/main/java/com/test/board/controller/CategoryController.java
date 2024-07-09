package com.test.board.controller;

import com.test.board.domain.CategoryDto;
import com.test.board.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<CategoryDto> list(int cno, String name) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCno(cno);
        categoryDto.setName(name);

        List<CategoryDto> categories = categoryService.getCategoryWithChildren(categoryDto);

        return categories;
    }
}