package basic_1;

public class Quack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println(" i can quack， 呱呱叫");
	}

}