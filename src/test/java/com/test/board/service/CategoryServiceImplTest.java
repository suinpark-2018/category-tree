package com.test.board.service;

import com.test.board.dao.CategoryDaoImpl;
import com.test.board.domain.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
class CategoryServiceImplTest {
    @Autowired CategoryServiceImpl categoryService;
    @Autowired CategoryDaoImpl categoryDao;

    @DisplayName("categoryService 주입 성공 테스트")
    @Test
    public void categoryServiceinjectSuccessTest() throws Exception {
        assertNotNull(categoryService);
    }

    // 방탄소년단 하위 카테고리로 공통의 익명게시판 추가 성공여부 테스트
    @DisplayName("익명게시판 추가 성공 테스트")
    @Test
    public void setAnonymousCategorySuccessTest() throws Exception {
        CategoryDto testDto = new CategoryDto();
        testDto.setCno(4);
        testDto.setName("방탄소년단");

        List<CategoryDto> categories = categoryDao.selectWithChildren(testDto);
        Map<Integer, CategoryDto> categoryMap = new HashMap<>();
        for (CategoryDto category : categories) {
            categoryMap.put(category.getCno(), category);
        }

        categoryService.setAnonymousCategory(categoryMap);

        int expectedCno = 14; // 익명게시판 cno
        int actualCno = categoryService.getCategoryWithChildren(testDto).get(0).getChildren().get(0).getCno();
        assertEquals(expectedCno, actualCno);
    }

    // 하위 카테고리 조회 성공 테스트
    // level1, level2 의 하위 카테고리 존재여부 확인
    // level1, level2 의 하위 카테고리 조회 성공 확인
    @DisplayName("level1 하위 카테고리 조회 성공 테스트")
    @Test
    public void level1_getCategoryWithChildrenSuccessTest() {
        // level1 카테고리의 cno, name 지정
        CategoryDto testDto = new CategoryDto();
        testDto.setCno(1);
        testDto.setName("남자");

        List<CategoryDto> result = categoryService.getCategoryWithChildren(testDto);
        assertNotNull(result);
        assertTrue(!result.isEmpty() || result.size() > 0);

        for (CategoryDto childDto : result) {
            assertNotNull(childDto.getChildren());
            assertTrue(!childDto.getChildren().isEmpty() || childDto.getChildren().size() > 0);
        }
    }

    @DisplayName("level2 하위 카테고리 조회 성공 테스트")
    @Test
    public void level2_getCategoryWithChildrenSuccessTest() {
        // level2 카테고리의 cno, name 지정
        CategoryDto testDto = new CategoryDto();
        testDto.setCno(4);
        testDto.setName("방탄소년단");

        List<CategoryDto> result = categoryService.getCategoryWithChildren(testDto);
        assertNotNull(result);
        assertTrue(!result.isEmpty() || result.size() > 0);

        for (CategoryDto childDto : result) {
            assertNotNull(testDto.getChildren());
            assertTrue(!childDto.getChildren().isEmpty() || childDto.getChildren().size() > 0);
        }
    }

    // 하위 카테고리 조회 실패 테스트
    // level3 하위 카테고리 존재여부 확인
    // level3 하위 카테고리 빈 배열임을 확인
    @DisplayName("하위 카테고리 조회 실패 테스트")
    @Test
    public void getCategoryWithChildrenFailTest() {
        CategoryDto testDto = new CategoryDto();
        testDto.setCno(11);   // level 3 카테고리 cno
        testDto.setName("뷔"); // level 3 카테고리 name

        List<CategoryDto> result = categoryService.getCategoryWithChildren(testDto);
        assertNotNull(result);
        assertTrue(!result.isEmpty() || result.size() > 0);

        for (CategoryDto childDto : result) {
            assertTrue(childDto.getChildren().isEmpty() || childDto.getChildren().size() == 0);
        }
    }
}