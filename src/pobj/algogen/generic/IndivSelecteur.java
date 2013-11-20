package pobj.algogen.generic;

public interface IndivSelecteur<T> {
	public Individu<T> getRandom(Population<T> pop);
}
