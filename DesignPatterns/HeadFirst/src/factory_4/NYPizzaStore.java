package factory_4;

public class NYPizzaStore extends PizzaStore{

	@Override
	protected Pizza createPizza(String type) {
		
		Pizza pizza;
		
		PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
		
		if (type.equals("cheese")) {
			pizza = new ChicagoStyleCheesePizza(ingredientFactory);
			pizza.setNmae("chigago style piiza");
			return pizza;
		} else {
			return null;
		}
	}

}
