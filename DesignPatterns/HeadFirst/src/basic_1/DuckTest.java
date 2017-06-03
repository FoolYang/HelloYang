package basic_1;

public class DuckTest {
	public static void main(String[] args) {
		Duck mallardDuck = new MallardDuck();
		mallardDuck.dispaly();
		mallardDuck.performFly();
		mallardDuck.performQuack();
		System.out.println("---change---");
		mallardDuck.setFlyBehavior(new FlyNoWay());
		mallardDuck.performFly();
	}
}
