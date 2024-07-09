package com.test.board.service;

import com.test.board.dao.CategoryDaoImpl;
import com.test.board.domain.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDaoImpl categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDaoImpl categoryDao) {
        this.categoryDao = categoryDao;
    }

    // 익명게시판을 부모 카테고리의 자식으로 추가
    @Override
    public void setAnonymousCategory(Map<Integer, CategoryDto> categoryMap) {
        try {
            CategoryDto anonymousBoard = categoryDao.select(14);
            // 방탄소년단 하위에 추가
            CategoryDto btsCategory = categoryMap.get(4);
            if (btsCategory != null) {
                btsCategory.getChildren().add(anonymousBoard);
            }

            // 블랙핑크 하위에 추가
            CategoryDto blackpinkCategory = categoryMap.get(5);
            if (blackpinkCategory != null) {
                blackpinkCategory.getChildren().add(anonymousBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 식별번호 및 카테고리명으로 특정 카테고리 정보 조회
    // 해당 카테고리의 하위 카테고리 정보도 함께 조회
    @Override
    public List<CategoryDto> getCategoryWithChildren(CategoryDto categoryDto) {

        // 특정 카테고리 및 해당 카테고리의 하위 카테고리 정보를 전체 저장하기 위한 리스트 선언
        List<CategoryDto> categories;

        // 선택된 카테고리와 해당 카테고리의 children 리스트만 저장된 리스트 선언
        List<CategoryDto> result = null;

        int cno = categoryDto.getCno();
        String name = categoryDto.getName();

        // 예외 처리
        try {

            // 매개변수로 받는 cno 및 name 유효성 검증
            // 카테고리 식별번호는 1 이상
            // 카테고리 이름은 반드시 존재해야 하고 공백 불가
            if (cno > 0 && !name.isEmpty() && !name.isBlank()) {
                categories = categoryDao.selectWithChildren(categoryDto);

                // 카테고리 식별번호로 카테고리 매핑
                // key: cno, value: 카테고리 정보가 담긴 dto 객체
                Map<Integer, CategoryDto> categoryMap = new HashMap<>();
                for (CategoryDto category : categories) {
                    categoryMap.put(category.getCno(), category);
                }

                setAnonymousCategory(categoryMap);

                // parent-child 관계 설정
                // 부모 카테고리 유무 확인
                // 객체 내 부모 카테고리 식별번호(parent_idx)와 동일한 cno 조회여부 확인
                // 부모가 있는 경우
                // 부모 객체의 children 리스트에 현재 카테고리 추가
                for (CategoryDto category : categories) {
                    if (category.getParent_idx() != 0) {
                        CategoryDto parentCategory = categoryMap.get(category.getParent_idx());
                        if (parentCategory != null) {
                            parentCategory.getChildren().add(category);
                        }
                    }
                }

                // 선택된 카테고리 객체 내 children 리스트에 하위 카테고리 정보 저장한 리스트만 선택적으로 반환
                result = new ArrayList<>();
                result.add(categoryMap.get(cno));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}