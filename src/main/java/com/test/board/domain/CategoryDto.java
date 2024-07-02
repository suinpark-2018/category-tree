package com.test.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDto {
    private int cno; // 카테고리 식별번호
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name; // 카테고리명
    private int level; // 카테고리 분류 (1: 성별, 2: 그룹명, 3: 공지사항 or 멤버별 게시판, 익명게시판)
    private int parent_idx; // 상위 카테고리 식별번호(cno)
    private String anony_state; // 익명게시판 여부
    private String active_state; // 카테고리 활성화 여부
    private List<CategoryDto> children = new ArrayList<>(); // 하위 카테고리를 저장하기 위한 리스트
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date reg_date; // 최초 등록일시
    private String reg_id; // 최초 등록자
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date up_date; // 최종 수정일시
    private String up_id; // 최종 수정자

    public CategoryDto() {}

    // Getters and Setters

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParent_idx() {
        return parent_idx;
    }

    public void setParent_idx(int parent_idx) {
        this.parent_idx = parent_idx;
    }

    public String getAnony_state() {
        return anony_state;
    }

    public void setAnony_state(String anony_state) {
        this.anony_state = anony_state;
    }

    public String getActive_state() {
        return active_state;
    }

    public void setActive_state(String active_state) {
        this.active_state = active_state;
    }

    public List<CategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "cno=" + cno +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parent_idx=" + parent_idx +
                ", anony_state='" + anony_state + '\'' +
                ", active_state='" + active_state + '\'' +
                ", children=" + children +
                '}';
    }
}
