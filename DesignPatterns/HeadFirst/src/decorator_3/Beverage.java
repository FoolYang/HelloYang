package decorator_3;
/**
 * 咖啡的基类
 * @author liyang
 *
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
	
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}
