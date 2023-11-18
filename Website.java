package observerdesignpattern;

import java.util.ArrayList;

interface IObservable {
	void add(Observer obs);
	void rm(Observer obs);
	void inform(String message);
}

interface IObservers {
	void update(String message);
}

class Observable implements IObservable {
	
	private final ArrayList<Observer> visitors = new ArrayList<>();
	
	
	
	@Override
	public void add(Observer obs) {
		// TODO Auto-generated method stub
		visitors.add(obs);
	}

	@Override
	public void rm(Observer obs) {
		// TODO Auto-generated method stub
		visitors.remove(obs);
	}

	@Override
	public void inform(String message) {
		// TODO Auto-generated method stub
		for(Observer obs : visitors) {
			obs.update(message);
		}
	}
	
}

class Observer implements IObservers {
	private final String name;
	Observable obs = new Observable();
	
	public Observer(Observable obs, String name){
		this.name = name;
		this.obs = obs;
		this.obs.add(this);
	}
	
	
	@Override
	public void update(String message) {
		// TODO Auto-generated method stub
		System.out.println("To: " + name + ", Sending this message : " + message);
	}
	
}

public class Website {
	public static void main (String[] args) {
		Observable obs = new Observable();
		Observer ob = new Observer(obs, "Jack");
		Observer ob2 = new Observer(obs, "Seif");
		obs.inform("THi is ");
		obs.inform("new update got released");
	}
}
