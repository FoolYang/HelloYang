package basic_1;

/**
 * 绿头鸭
 * 会飞，会quack（呱呱叫）
 * @author liyang
 *
 */
public class MallardDuck extends Duck {
	
	public MallardDuck() {
		mFlyBehavior = new FlyWithWings();
		mQuackBehavior = new Quack();
	}

	@Override
	public void dispaly() {
		System.out.println("i am a mallard duck");
	}

}
