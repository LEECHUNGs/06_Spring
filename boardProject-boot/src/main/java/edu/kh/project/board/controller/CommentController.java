package edu.kh.project.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




/* @RestController (REST Api 구축을 위해 사용되는 컨트롤러)
 * 
 * = @Controller + @ResponseBody
 * 
 * -> 모든 응답을 본문(ajax)으로 반환하는 컨트롤러
 * 
 * */

//@Controller // Controller 명시 + Bean으로 등록

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
	
	// fetch - 비동기요청
	// "/comment" 요청이 오면 해당 컨트롤레어서 잡아서 처리
	// @ResponseBody를 매번 메서드에 추가...
	
	private final CommentService service;
	
	/** 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping("")
	public List<Comment> select(@RequestParam("boardNo") int boardNo) {
		
		// HttpMessageConverter 가
		// 
		
		return service.select(boardNo);
	}
	
	/** 댓글/답글 등록
	 * @return
	 */
	@PostMapping("")
	public int insert(@RequestBody Comment comment) {
		
		return service.insert(comment);
	}
	
	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		return service.update(comment);
	}
	
	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
		return service.delete(commentNo);
	}
}







