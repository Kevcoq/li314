package pobj.obs;

public interface ISimpleObservable {
	public void addObserver(ISimpleObserver obs);

	public void deleteObserver(ISimpleObserver obs);
}
