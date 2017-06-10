package factory_4;

public class PizzaTestDrive {
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		Pizza pizza = nyStore.createPizza("cheese");
		System.out.println("ordered a " + pizza.getName());
	}
}
