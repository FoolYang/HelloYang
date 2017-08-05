package state_10;

public class WinnerState implements State{
	private GumballMachine gumballMachine;
	
	public WinnerState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("winner insertQuarter wrong...");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("winner ejectQuarter wrong...");
	}

	@Override
	public void turnCrank() {
		System.out.println("winner turnCrank wrong...");
		
	}

	@Override
	public void dispense() {
		System.out.println("you are a winner, you get two gumballs for your quarter");
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState());
		} else {
			gumballMachine.releaseBall();
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("oops, out of gumballs");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
	}

}
