package decorator_3;
/**
 * 奶泡是一个装饰者
 * @author liyang
 *
 */
public class Whip extends CondimentDecorator{
	Beverage berverage; // 用一个实例来记录饮料，也就是被装饰者
	
	public Whip(Beverage beverage) {
		this.berverage = beverage;
	}

	@Override
	public String getDescription() {
		return berverage.getDescription() + ", Whip";
	}

	@Override
	public double cost() {
		return 2.2 + berverage.cost();
	}

}
