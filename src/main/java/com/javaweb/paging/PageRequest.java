package com.javaweb.paging;

import com.javaweb.sort.Sorter;

public class PageRequest implements IPageable{
	
	private Integer crpage;
	private Integer maxPageItem;
	private Sorter sorter;
	
	public PageRequest(Integer crpage, Integer maxPageItem, Sorter sorter) {
		this.crpage = crpage;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public Integer getCurrentPage() {
		
		return this.crpage;
	}

	@Override
	public Integer getOffset() {
		if (this.crpage != null && this.maxPageItem != null) {
			return (this.crpage - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getVisiblePage() {
		
		return this.maxPageItem;
	}

	public Sorter getSorter() {
		if (this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}
	
}
