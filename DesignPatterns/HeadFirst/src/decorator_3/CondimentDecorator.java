package decorator_3;
/**
 * 抽象装饰者
 * 装饰者基类，因为要取代咖啡类，所以继承自咖啡基类
 * @author liyang
 *
 */
public abstract class CondimentDecorator extends Beverage{

	// 装饰者需要重新实现getDescription方法
	public abstract String getDescription();

}
