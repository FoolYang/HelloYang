package factory_4;
/**
 * 纽约原料工厂
 * 
 * 具体的抽象工厂类
 * 
 * @author liyang
 *
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory{
	
	public NYPizzaIngredientFactory(){
	
	}

	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinariSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

}
