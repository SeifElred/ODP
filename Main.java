package observerdesignpattern;

import java.util.ArrayList;
import java.util.List;

interface EventListener {
	void update(String message);
}

public class Main {
	private final NotificationService notificationService;

	public static void main(String[] args) {
		Main main = new Main();
		main.subscribeListeners();
		main.newItemPromotion();
	}
	
	
	public Main() {
		notificationService = new NotificationService();
	}
	
	public void subscribeListeners() {
		notificationService.subscribe(new MailMsgListener("seif@gmial.com"));
		notificationService.subscribe(new MailMsgListener("eela@gmial.com"));
		notificationService.subscribe(new MobileAppListener("seifelred"));
	}
	
	public void newItemPromotion() {
		notificationService.nofityListeners("new item promotion is now available");
	}

}

class NotificationService {
	private final List<EventListener> customers; //  Keeping track of the customers who want to receive our updates via mail

	
	public NotificationService() {
		customers = new ArrayList<>();
	}
	
	public void subscribe(EventListener listener) {
		customers.add(listener);
	}
	
	public void unsubscribe(EventListener listener) {
		customers.remove(listener);
	}
	
	public void nofityListeners(String message) {
		for(EventListener customer : customers) {
			customer.update(message);
		}
	}
	
}

class MailMsgListener implements EventListener {
	private final String email;
	
	public MailMsgListener(String email) {
		this.email = email;
	}
	
	@Override
	public void update(String message) {
		// Actually send the Email
		System.out.println("Sending an Email to " + email + " : " + message);
	}
	
}

class MobileAppListener implements EventListener {
	private final String username;
	
	public MobileAppListener(String username) {
		this.username = username;
	}
	
	@Override
	public void update(String message) {
		// Actually send the Email
		System.out.println("Sending push Notification to user " + username + " : " + message);
	}
	
}