package decorator_3;
/**
 * 浓缩咖啡类
 * @author liyang
 *
 */
public class Espresso extends Beverage{
	
	public Espresso() {
		description = "Espresso";
	}

	@Override
	public double cost() {
		return 1.99;
	}

}
