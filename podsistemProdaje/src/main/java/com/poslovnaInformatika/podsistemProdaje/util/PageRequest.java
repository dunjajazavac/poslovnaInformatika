package com.poslovnaInformatika.podsistemProdaje.util;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequest extends AbstractPageRequest {
	
	private static final long serialVersionUID = 1L;

	public PageRequest(int page, int size) {
		super(page, size);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

}
