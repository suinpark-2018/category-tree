package com.test.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.test.board.domain.CategoryDto;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    // MyBatis 의 핵심 객체로, SQL 명령어를 실행하고 데이터베이스와 상호 작용하는 데 사용됨
    // SQL 명령문을 실행하기 위한 메서드 제공
    @Autowired SqlSession session;

    // namespace: MyBatis 가 mapper 파일 내의 SQL 문을 유일하게 식별할 수 있도록 함
    // 같은 namespace (scope) 내에서는 SQL 문장들을 공유할 수 있음.
    private static String namespace = "com.test.board.dao.CategoryDao.";

    // 전체 카테고리 정보 조회
    @Override
    public List<CategoryDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    // 특정 카테고리 정보 조회
    @Override
    public CategoryDto select(int cno) throws Exception {
        return session.selectOne(namespace + "select", cno);
    }

    // 특정 상위 카테고리의 하위 카테고리 조회
    // 카테고리 식별자 및 카테고리명으로 조회
    @Override
    public List<CategoryDto> selectWithChildren(CategoryDto categoryDto) throws Exception {
        return session.selectList(namespace + "selectWithChildren", categoryDto);
    }
}