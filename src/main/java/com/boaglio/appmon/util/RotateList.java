package com.boaglio.appmon.util;

import java.util.ArrayList;

import com.boaglio.appmon.dto.Config;

public class RotateList<E> extends ArrayList<E> {

	private static final long serialVersionUID = 3887688969101881899L;

	@Override
	public boolean add(E e) {

		if (this.size() >= Config.getInstance().getRetentionSize()) {
			this.remove(this.size() - 1);
		}

		return super.add(e);
	}
}
