package pobj.obs;

import java.util.ArrayList;

public class SimpleObservable implements ISimpleObservable {
	private ArrayList<ISimpleObserver> obs;

	public SimpleObservable() {
		super();
		obs = new ArrayList<ISimpleObserver>();
	}

	@Override
	public void addObserver(ISimpleObserver obs) {
		// TODO Auto-generated method stub
		this.obs.add(obs);
	}

	@Override
	public void deleteObserver(ISimpleObserver obs) {
		// TODO Auto-generated method stub
		this.obs.remove(obs);
	}

	public void notifyObservers() {
		for (ISimpleObserver o : obs)
			o.update();
	}
}
