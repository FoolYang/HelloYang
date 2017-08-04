package iterator_9;

public class DinerMenu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	MenuItem[] menuItems;
	
	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
		
		addItem("vegetarian blt", "fakin bacon with lettuce tomato on whole wheat", true, 2.99);
		addItem("blt", "bacon with lettuce tomato on whole wheat", false, 2.99);
		addItem("soup of the day", "with a side of potato salad", false, 3.29);
		addItem("hotdog", "with saurkaut relish onions", false, 3.05);
		// others...
		
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if (numberOfItems >= MAX_ITEMS) {
			System.out.println("sorry menu is full! can not add item to menu");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}
	
//	public MenuItem[] getMenuItems() {
//		return menuItems;
//	}
	
	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}

}
