package com.test.board.dao;

import com.test.board.domain.CategoryDto;

import java.util.List;

public interface CategoryDao {
    List<CategoryDto> selectAll() throws Exception;
    CategoryDto select(int cno) throws Exception;

    // 특정 상위 카테고리의 하위 카테고리 조회
    // 카테고리 식별자 및 카테고리명으로 조회
//    List<CategoryDto> selectWithChildren(int cno, String name) throws Exception;
    List<CategoryDto> selectWithChildren(CategoryDto categoryDto) throws Exception;
}
