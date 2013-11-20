package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import pobj.util.MyArrayList;

public class MyArrayListTest extends TestCase {
	private MyArrayList<Integer> list;
	private MyArrayList<Integer> listCap;
	private MyArrayList<Integer> listCol;

	protected void setUp() throws Exception {
		super.setUp();
		List<Integer> col = new ArrayList<Integer>();
		list = new MyArrayList<>();
		listCap = new MyArrayList<>(2);
		list.add(5);
		listCap.add(5);
		col.add(5);
		for (int i = 0; i < 10; i++) {
			list.add(i);
			listCap.add(i);
			col.add(i);
		}
		listCol = new MyArrayList<>(col);
	}

	public void testAdd() {
		assert (list.add(5) == true);
		assert (listCap.add(5) == true);
		assert (listCol.add(5) == true);
	}

	public void testGet() {
		assert (list.get(10) == 9);
		assert (listCap.get(10) == 9);
		assert (listCol.get(10) == 9);
	}

	public void testSet() {
		System.out.println(listCol.size());
		list.set(10, 20);
		listCap.set(10, 20);
		listCol.set(10, 20);
		assert (list.get(10) == 20);
		assert (listCap.get(10) == 20);
		assert (listCol.get(10) == 20);
	}

	public void testSize() {
		assert (list.size() == 11);
		assert (listCap.size() == 11);
		assert (listCol.size() == 11);
	}

}
