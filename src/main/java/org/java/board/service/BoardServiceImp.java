package org.java.board.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.java.board.aop.BoardAscpect;
import org.java.board.dao.BoardDao;
import org.java.board.dto.BoardDto;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

public class BoardServiceImp implements BoardService {
	private BoardDao boardDao;

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public void boardWriter(ModelAndView mav) {
		int boardNumber=0;		
		int groupNumber=1;		
		int sequenceNumber=0;	
		int sequenceLevel=0;	
		
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		if(request.getParameter("boardNumber") !=null){  	
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("groupNumber",groupNumber);
		mav.addObject("sequenceNumber",sequenceNumber);
		mav.addObject("sequenceLevel",sequenceLevel);
		
		// InternalResourceView view=new InternalResourceView();
		// view.setUrl("/WEB-INF/view/board/write.jsp");
		// mav.setView(view);
		
		mav.setViewName("board/write");
		
		// mav.setViewName("forward:/aaa/aa.do");
		// mav.setViewName("redirect:/aaa/aa.do(jsp)");
	}
	
	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		BoardDto boardDto=(BoardDto) map.get("boardDto");
		
		boardWriterNumber(boardDto);
		
		boardDto.setWriteDate(new Date());
		boardDto.setReadCount(0);
		
		int check=boardDao.boardWrite(boardDto);
		BoardAscpect.logger.info(BoardAscpect.logMsg +  check);
		
		mav.addObject("check", check);
		mav.setViewName("board/writeOk");
	}
	
	public void boardWriterNumber(BoardDto boardDto) {
		int boardNumber=boardDto.getBoardNumber();
		int groupNumber=boardDto.getGroupNumber();
		int sequenceNumber=boardDto.getSequenceNumber();
		int sequenceLevel=boardDto.getSequenceLevel();
		
		if(boardNumber==0) {    // 그룹번호
			int max=boardDao.boardGroupNumberMax();
			BoardAscpect.logger.info(BoardAscpect.logMsg + max);
			
			if(max !=0) boardDto.setGroupNumber(max+1);
		}else {      // 글순서, 글레벨
			
		}
	}

	@Override
	public void boardList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");	
		
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int boardSize=10;
		int currentPage=Integer.parseInt(pageNumber);
		int startRow=(currentPage-1)*boardSize+1;			
		int endRow=currentPage*boardSize;		
		
		int count=boardDao.boardCount();
		BoardAscpect.logger.info(BoardAscpect.logMsg +  count);
		
		List<BoardDto> boardList=null;
		if(count > 0){
			boardList=boardDao.boardList(startRow, endRow);
		}
		BoardAscpect.logger.info(BoardAscpect.logMsg +  boardList.size());
		
		mav.addObject("boardList", boardList);
		mav.addObject("count", count);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		
		mav.setViewName("board/list");
		
	}
	
	@Override
	public void boardRead(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");	
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber").trim());
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		BoardDto boardDto=boardDao.boardRead(boardNumber);
		BoardAscpect.logger.info(BoardAscpect.logMsg + "boardDto:" + boardDto.toString());

		mav.addObject("boardDto", boardDto);
		mav.addObject("pageNumber", pageNumber);
		
		mav.setViewName("board/read");		
	}
}
































