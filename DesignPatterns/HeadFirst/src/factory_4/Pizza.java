package factory_4;

import java.util.ArrayList;

/**
 * Pizza 的抽象类
 * @author liyang
 *
 */

public abstract class Pizza {
	String name;
	Dough dough;
	Sauce sauce;
	Cheese cheese;
	ArrayList toppings = new ArrayList();

//	public void prepare() {
//		System.out.println("prepareing " + name);
//		System.out.println("tossing dough...");
//		System.out.println("adding sauce...");
//		System.out.println("adding toppings:");
//		for(int i = 0; i < toppings.size(); i++) {
//			System.out.println("   " + toppings.get(i));
//		}
//	}
	
	public abstract void prepare();
	
	public void bake() {
		System.out.println("bake for 25 minutes at 350");
	}
	
	public void cut() {
		System.out.println("cutting the pizza into diagonal slices");
	}
	
	public void box() {
		System.out.println("place pizza in official pizzastore box");
	}
	
	public String getName() {
		return name;
	}
	
	public void setNmae(String name) {
		this.name = name;
	}
}
