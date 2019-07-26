package org.java.board.dao;

import java.util.HashMap;
import java.util.List;

import org.java.board.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;

public class BoardDaoImp implements BoardDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public int boardGroupNumberMax() {
		return sqlSessionTemplate.selectOne("boardGroupNumberMax");
	}

	@Override
	public int boardWrite(BoardDto boardDto) {
		return sqlSessionTemplate.insert("boardInsert", boardDto);
	}
	
	@Override
	public int boardCount() {
		return sqlSessionTemplate.selectOne("boardCount");
	}
	
	@Override
	public List<BoardDto> boardList(int startRow, int endRow) {
		HashMap<String, Integer> hMap=new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		
		return sqlSessionTemplate.selectList("boardList", hMap);
	}
	
	@Override
	public BoardDto boardRead(int boardNumber) {
		sqlSessionTemplate.update("boardReadCount", boardNumber);	
		return sqlSessionTemplate.selectOne("boardRead", boardNumber);
	}
}












