package factory_4;
/**
 * pizza 原料
 * 抽象工厂的接口
 * @author liyang
 *
 */
public interface PizzaIngredientFactory {
	Dough createDough();
	Sauce createSauce();
	Cheese createCheese();
}
