package com.test.board.dao;

import com.test.board.domain.CategoryDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    // SqlSession: SQL 명령문을 실행하기 위한 메서드 제공
    @Autowired SqlSession session;

    // namespace: scope 을 지정해주는 유일한 식별자
    // 같은 namespace 내에서는 SQL 문장들을 공유할 수 있음.
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
    public List<CategoryDto> selectWithChildren(int cno, String name) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("cno", cno);
        params.put("name", name);
        return session.selectList(namespace + "selectWithChildren", params);
    }
}
