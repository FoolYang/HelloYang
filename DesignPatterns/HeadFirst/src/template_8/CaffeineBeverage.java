package template_8;

public abstract class CaffeineBeverage {
	final void prepareRecipe() {
		boilWater();
		brew();
		pourIncup();
		addCondiments();
	}
	
	abstract void brew();
	

	private void boilWater() {
		System.out.println("boiling water");
	}
	
	private void pourIncup() {
		System.out.println("pouring into cup");
	}
		
	abstract void addCondiments();
}
