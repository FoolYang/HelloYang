package factory_4;
/**
 * 具体的pizza类 芝加哥pizza
 * @author liyang
 *
 */
public class ChicagoStyleCheesePizza extends Pizza{
	PizzaIngredientFactory ingredientFactory;
	
	public ChicagoStyleCheesePizza(PizzaIngredientFactory ingredientFactory) {
		name = "chicago style deep dish cheese pizza";
//		dough = "extra thick crust dough";
//		sauce = "plum tomato sauce";
		
		toppings.add("shredded mozzarella cheese");
		
		this.ingredientFactory = ingredientFactory;
	}
	
	
	// 芝加哥风味 pizza 覆盖了cut方法，将pizza切成了正方形
	@Override
	public void cut() {
		System.out.println("cutting the pizza into square sices");
	}


	@Override
	public void prepare() {
		System.out.println("preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
