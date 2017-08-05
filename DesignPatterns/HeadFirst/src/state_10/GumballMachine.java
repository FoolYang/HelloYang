package state_10;

public class GumballMachine {
	
	private State soldOutState; // 糖果售磬
	private State noQuarterState; // 没有25分钱
	private State hasQuarterState; // 有25分钱
	private State soldSate; // 售出糖果
	private State winnerState; // 赢家
	
	private int count = 0;
	private State state = soldOutState;
	
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldSate = new SoldState(this);
		winnerState = new WinnerState(this);
		this.count = numberGumballs;
		if (count > 0) {
			state = noQuarterState;
		}
	}
	
	/**
	 * 用户投入25分钱
	 */
	public void insertQuarter() {
		state.insertQuarter();
	}
	
	/**
	 * 用户尝试退回25分钱
	 */
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	/**
	 * 用户尝试转动曲柄
	 */
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}

	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getHasQuarterState() {
		return hasQuarterState;
	}
	
	public State getNoQuarterState() {
		return noQuarterState;
	}
	
	public State getSoldState() {
		return soldSate;
	}
	
	public State getSoldOutState() {
		return soldOutState;
	}

	public State getWinnerState() {
		return winnerState;
	}
	
	public void releaseBall() {
		System.out.println("a gumball comes rolling out the slot...");
		if (count != 0) {
			count = count - 1;
		}
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		System.out.println("-- state:" + state);
		return "";
	}

}
