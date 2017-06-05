package decorator_3;
/**
 * 摩卡是一个装饰者
 * @author liyang
 *
 */
public class Mocha extends CondimentDecorator{
	Beverage berverage; // 用一个实例来记录饮料，也就是被装饰者
	
	public Mocha(Beverage beverage) {
		this.berverage = beverage;
	}

	@Override
	public String getDescription() {
		return berverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return 2.0 + berverage.cost();
	}

}
