package factory_4;

/**
 * Pizza 工厂的抽象类
 * @author liyang
 *
 */

public abstract class PizzaStore {
	
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	protected abstract Pizza createPizza(String type);
	
	// 其它方法
}
