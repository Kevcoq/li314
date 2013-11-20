package pobj.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

class MyIterator<T> implements Iterator<T> {
	private Iterator<Vector<T>> listIT;
	private Iterator<T> vectIT;

	public MyIterator(LinkedList<Vector<T>> list) {
		listIT = list.iterator();
		vectIT = Collections.emptyIterator();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return listIT.hasNext() || vectIT.hasNext();
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if (vectIT.hasNext())
			return vectIT.next();
		listIT.next();
		vectIT = listIT.next().iterator();
		return vectIT.next();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"Op�ration remove non impl�ment� dans MyIterator");
	}
}
