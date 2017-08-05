package state_10;

import java.util.Random;

public class HasQuarterState implements State {
	private GumballMachine gumballMachine;
	private Random randomWindow = new Random();
	
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("you can't insert another quarter");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("quarter returned");
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	@Override
	public void turnCrank() {
		System.out.println("you turned...");
		int winner = randomWindow.nextInt(10);
		if (winner == 0 && gumballMachine.getCount() > 1) {
			gumballMachine.setState(gumballMachine.getWinnerState());
		} else {
			gumballMachine.setState(gumballMachine.getSoldState());
		}
	}

	@Override
	public void dispense() {
		System.out.println("no gumball dispensed");
	}

}
