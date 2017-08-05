package state_10;

public class SoldOutState implements State{
	private GumballMachine gumballMachine;
	
	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("you can't insert quarter, the machine is sold out");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("you can't eject, you haven't insert quarter yet");
	}

	@Override
	public void turnCrank() {
		System.out.println("you turned, but there is no gumball");
	}

	@Override
	public void dispense() {
		System.out.println("no gumballs diepensed");
	}

}
