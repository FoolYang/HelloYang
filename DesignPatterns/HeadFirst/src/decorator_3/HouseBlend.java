package decorator_3;
/**
 * 另外一种饮料
 * @author liyang
 *
 */
public class HouseBlend extends Beverage{
	
	public HouseBlend() {
		description = "HouseBlend";
	}

	@Override
	public double cost() {
		return 0.89;
	}

}
