package org.java.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java.board.dto.BoardDto;
import org.java.board.service.BoardService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BoardController extends MultiActionController{
	private BoardService  boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardWriter(mav);
		
		return mav;
		
		// public String - return "redirect:/ViewResolver/ViewResolver.jsp";
	}
	
	public ModelAndView boardOkWrite(HttpServletRequest request, 
						HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("boardDto", boardDto);
		
		boardService.boardWriteOk(mav);
		
		return mav;
	}
	
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardList(mav);
		return mav;
	}
	
	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response){		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardRead(mav);
		return mav;
	}
}














