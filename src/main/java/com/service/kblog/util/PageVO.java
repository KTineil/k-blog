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
		setStartPageOfRange();
		setEndPageOfRange();
		this.limitStartIndex = (page-1) * size;
		this.prevPage = page == 1 ? -1 : page - 1;
		this.nextPage = page == lastPage ? -1 : page + 1;
	}
	
	public void setStartPageOfRange() {
		
		// 마지막 페이지가 페이지범위에 있으면 시작 페이지 고정 
		if (page + 2 >= lastPage) {
			if (lastPage <= 5) {
				startPageOfRange = 1;
			}
			else {
				startPageOfRange = lastPage - 4;
			}
		}
	
		// 3보다 페이지가 작으면 범위 넘어감 효과X
		else if (page < 3) {
			startPageOfRange = 1;
		}
		
		// 두 예 경우를 제외하고 일반적인 페이지 넘김 처
		else {
			startPageOfRange = page - 2;
		}
	}
	
	public void setEndPageOfRange() {
		endPageOfRange = lastPage - startPageOfRange < 4 ? lastPage : startPageOfRange + 4;
	}
}
