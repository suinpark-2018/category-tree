package com.test.board.service;

import com.test.board.domain.CategoryDto;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    // 익명게시판을 부모 카테고리의 자식으로 추가
    void setAnonymousCategory(Map<Integer, CategoryDto> categoryMap);

    // 카테고리 식별번호 및 카테고리명으로 특정 카테고리 정보 조회
    // 해당 카테고리의 하위 카테고리 정보도 함께 조회
    List<CategoryDto> getCategoryWithChildren(CategoryDto categoryDto);
//    List<CategoryDto> getCategoryWithChildren(int cno, String name);
}
