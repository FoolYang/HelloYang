package state_10;

/**
 * 定义所有状态的接口。这些方法直接映射到糖果机上可能发生的动作。
 * @author liyang
 *
 */
public interface State {
	void insertQuarter();
	void ejectQuarter();
	void turnCrank();
	void dispense();
}
