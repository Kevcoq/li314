package pobj.algogen.generic;

public interface IEvolution<T> {
	public Population<T> reproduire(Population<T> pop, double ratio);
}
