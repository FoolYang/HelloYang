package basic_1;

/*
 * 鸭子的抽象类，所以鸭子都需要继承此类
 * 组合了行为接口：飞，叫
 */
public abstract class Duck {
	FlyBehavior mFlyBehavior;
	QuackBehavior mQuackBehavior;
	
	public void swim() {
		System.out.println(" i can  swim ");
	}
	
	public abstract void dispaly();
	
	public void performFly() {
		if (mFlyBehavior != null) {
			mFlyBehavior.fly();
		}
	}
	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.mFlyBehavior = flyBehavior;
	}
	
	public void performQuack() {
		if (mQuackBehavior != null) {
			mQuackBehavior.quack();
		}
	}

}
