package com.javaweb.paging;

import com.javaweb.sort.Sorter;

public interface IPageable {
	Integer getCurrentPage();
	Integer getOffset();
	Integer getVisiblePage();
	Sorter getSorter();
}
