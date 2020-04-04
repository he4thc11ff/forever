package com.effective.section5.demo23;

import java.util.ArrayList;
import java.util.List;

public class MyList<E> {

	private List<E> e = new ArrayList<>();

	public MyList() {
	}

	public void add(E e) {
		this.e.add(e);
	}

	public E get(int i) {
		return this.e.get(i);
	}

}
