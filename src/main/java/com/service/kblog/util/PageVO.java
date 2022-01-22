package com.service.kblog.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.kblog.persistence.PostRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class PageVO {
	
	@Autowired
	private PostRepository postRepository;
	
	private int page;
	
	private int startPageOfRange;
	
	private int endPageOfRange;
	
	private int lastPage;
	
	private int prevPage;
	
	private int nextPage;
	
	private int size;
	
	private int limitStartIndex;
	
	public PageVO() {
		this.page = 1;
		this.size = 5;
	}
	
	public void pagination(int cntOfPosts) {
		this.lastPage = cntOfPosts != 0 ? (int) Math.ceil( (double) cntOfPosts / 5) : 1;
		setStartPageOfrange();
		setEndPageOfRange();
		this.limitStartIndex = (page-1) * size;
		this.prevPage = page == 1 ? -1 : page - 1;
		this.nextPage = page == lastPage ? -1 : page + 1;
	}
	
	public void setStartPageOfrange() {
		if (lastPage - 4 <= 0) {
			startPageOfRange = 1;
		}
		else if (lastPage - 4 <= page) {
			startPageOfRange = lastPage - 4;
		}
		else {
			startPageOfRange = page;
		}
	}
	
	public void setEndPageOfRange() {
		endPageOfRange = lastPage - startPageOfRange < 4 ? lastPage : startPageOfRange + 4;
	}
}
