package pobj.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class MyArrayList<T> extends AbstractList<T> {
	private int capacite;
	private LinkedList<Vector<T>> list;

	public MyArrayList() {
		this(10);
	}

	public MyArrayList(int taille) {
		list = new LinkedList<Vector<T>>();
		capacite = taille;
	}

	public MyArrayList(Collection<T> list) {
		this();
		@SuppressWarnings("unchecked")
		T[] tab = (T[]) list.toArray();
		for (int i = 0; i < tab.length; i++) {
			add(tab[i]);
		}
	}

	public boolean add(T object) {
		/*
		 * NB : Si le dernier Vector est plein (size() = capacit�), en allouer
		 * un nouveau. Si la liste est vide, allouer un chainon. Pour allouer un
		 * vecteur capacit� N faire new Vector(N).
		 */
		if (list.size() == 0 || list.getLast().size() >= capacite)
			list.addLast(new Vector<T>(capacite));
		return list.getLast().add(object);
	}

	public T get(int location) {
		/*
		 * NB : on peut calculer directement avec des modulo et des divisions
		 * les bonnes positions
		 */
		return list.get(location / capacite).get(location % capacite);
	}

	public T set(int location, T object) {
		return list.get(location / capacite).set(location % capacite, object);
	}

	public int size() {
		/*
		 * NB : on pourrait mesurer juste le dernier vecteur et la taille de la
		 * liste
		 */
		return (list.size() - 1) * capacite + list.getLast().size();
	}

	public T remove(int i) {
		return list.get(i / capacite).remove(i % capacite);
	}
	
	public Iterator<T> iterator() {
		MyIterator<T> it = new MyIterator<T>(list);
		return it;
	}

	// public static void main(String[] args) {
	// MyArrayList<Integer> list;
	// MyArrayList<Integer> listCap;
	// MyArrayList<Integer> listCol;
	//
	// List<Integer> col = new ArrayList<Integer>();
	// list = new MyArrayList<>();
	// listCap = new MyArrayList<>(2);
	// list.add(5);
	// listCap.add(5);
	// col.add(5);
	// for (int i = 0; i < 10; i++) {
	// list.add(i);
	// listCap.add(i);
	// col.add(i);
	// }
	// listCol = new MyArrayList<>(col);
	// for (int i = 0; i < listCol.size(); i++)
	// System.out.println(listCol.get(i));
	// }
}
