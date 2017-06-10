package factory_4;
/**
 * 具体的pizza子类 纽约pizza
 * @author liyang
 *
 */
public class NYStyleCheesePizza extends Pizza{
	public NYStyleCheesePizza() {
//		name = "NY style sauce and cheese pizza";
//		dough = "thin crust dough";
//		sauce = "marinara sauce";
		
		toppings.add("grated reggiano cheese");
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		
	}

}
