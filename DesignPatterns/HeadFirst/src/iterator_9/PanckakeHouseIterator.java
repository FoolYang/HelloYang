package iterator_9;

import java.util.ArrayList;

public class PanckakeHouseIterator implements Iterator{
	int position = 0;
	ArrayList menuItems;
	
	public PanckakeHouseIterator(ArrayList menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public boolean hasNext() {
		
		return menuItems != null && position < menuItems.size();
		
	}

	@Override
	public Object next() {
		if (hasNext()) {
			MenuItem menuItem = (MenuItem)menuItems.get(position);
			position = position + 1;
			return menuItem;
		}
		
		return null;
	}

}
