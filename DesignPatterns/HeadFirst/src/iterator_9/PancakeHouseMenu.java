package iterator_9;

import java.util.ArrayList;

public class PancakeHouseMenu {
	ArrayList menuItems;
	
	public PancakeHouseMenu() {
		menuItems = new ArrayList();
		
		addItem("fb pancake breakfast", "pancake with scrambled eggs and toast", true, 2.99);
		addItem("regulat", "bacon with lettuce tomato on whole wheat", false, 2.99);
		addItem("blueberry", "with a side of potato salad", false, 3.49);
		addItem("waffles", "with saurkaut relish onions", false, 3.59);
		// others...
		
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}
	
//	public ArrayList getMenuItems() {
//		return menuItems;
//	}
	
	public Iterator createIterator() {
		return new PanckakeHouseIterator(menuItems);
	}
	

}
