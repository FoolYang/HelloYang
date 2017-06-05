package decorator_3;
/**
 * 豆浆是一个装饰者
 * @author liyang
 *
 */
public class Soy extends CondimentDecorator{
	Beverage berverage; // 用一个实例来记录饮料，也就是被装饰者
	
	public Soy(Beverage beverage) {
		this.berverage = beverage;
	}

	@Override
	public String getDescription() {
		return berverage.getDescription() + ", Soy";
	}

	@Override
	public double cost() {
		return 2.1 + berverage.cost();
	}

}
