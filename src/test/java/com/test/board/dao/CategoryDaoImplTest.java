package com.test.board.dao;

import com.test.board.domain.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CategoryDaoImplTest {
    @Autowired CategoryDaoImpl categoryDao;

    @DisplayName("categoryDao 주입 성공 테스트")
    @Test
    public void categoryDaoInjectSuccessTest() throws Exception {
        assertNotNull(categoryDao);
    }

    @DisplayName("전체 카테고리 조회 성공 테스트")
    @Test
    public void selectAllTest() throws Exception {
        // 1. 카테고리 전체 조회 시행여부 확인
        // 2. 조회된 카테고리가 하나 이상 있는지 확인
        // 3. 조회된 카테고리 개수가 기존에 더미데이터로 넣은 14개와 동일한지 확인

        // categoryDao 메서드를 사용하여 카테고리 경로 조회하고, 조회된 결과를 result 리스트에 저장
        List<CategoryDto> result = categoryDao.selectAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.size() == 14);
    }

    @DisplayName("특정 카테고리 정보 조회 성공 테스트")
    @Test
    public void selectSuccessTest() throws Exception {
        // 1. 조회된 특정 카테고리 존재하는지 확인
        // 2. 테스트 위해 설정한 값으로 출력되는지 확인
        int cno = 1;

        assertNotNull(categoryDao.select(cno));
        assertTrue(categoryDao.select(cno).getCno() == 1);
    }

    @DisplayName("level1 카테고리의 하위 카테고리 정보 조회 성공 테스트")
    @Test
    public void level1_selectWithChildrenSuccessTest() throws Exception {
        // 1. 특정 카테고리의 및 하위 카테고리 정보 조회
        // 2. 카테고리 식별자 및 카테고리명으로 검색

        // 남자 카테고리 및 하위 카테고리 정보 조회
        int cno1 = 1;
        String name1 ="남자";

        List<CategoryDto> result1 = categoryDao.selectWithChildren(cno1, name1);
        assertNotNull(result1);
        assertTrue(result1.size() > 0);

        // 특정 값이 포함되어 있는지 확인
        // 남자 그룹: 엑소, 방탄소년단
        boolean hasExo = result1.stream().anyMatch(category -> "엑소".equals(category.getName()));
        boolean hasBts = result1.stream().anyMatch(category -> "방탄소년단".equals(category.getName()));

        assertTrue(hasExo);
        assertTrue(hasBts);

        // 여자 카테고리 및 하위 카테고리 정보 조회
        int cno2 = 2;
        String name2 ="여자";

        List<CategoryDto> result2 = categoryDao.selectWithChildren(cno2, name2);
        assertNotNull(result2);
        assertTrue(result2.size() > 0);

        // 특정 값이 포함되어 있는지 확인
        // 여자 그룹: 블랙핑크
        boolean hasBlackPink = result2.stream().anyMatch(category -> "블랙핑크".equals(category.getName()));
        assertTrue(hasBlackPink);
    }

    @DisplayName("level2 카테고리의 하위 카테고리 조회 성공 테스트")
    @Test
    public void level2_selectWithChildrenSuccessTest() throws Exception {
        int cno1 = 3;
        String name1 = "엑소";
        List<CategoryDto> result = categoryDao.selectWithChildren(cno1, name1);

        assertNotNull(result);
        assertTrue(result.size() > 0);

        int expectedCno = cno1;
        int actualCno = result.get(0).getCno();
        assertEquals(expectedCno, actualCno);

        String expectedName = name1;
        String actualName = result.get(0).getName();
        assertEquals(expectedName, actualName);
    }

    @DisplayName("특정 카테고리의 하위 카테고리 정보 조회 실패 테스트")
    @Test
    public void selectWithChildrenFailTest() throws Exception {
        // 1. 특정 카테고리의 및 하위 카테고리 정보 조회
        // 2. 카테고리 식별자 및 카테고리명으로 검색

        // 잘못된 카테고리 번호 및 이름으로 조회 시
        int cno = 1;
        String name ="여자";

        List<CategoryDto> result = categoryDao.selectWithChildren(cno, name);
        assertTrue(result.isEmpty() || result.size() == 0);
    }
}