package template_8;

public class Coffe extends CaffeineBeverage{

	@Override
	void brew() {
		System.out.println("dripping coffe through filter");
	}

	@Override
	void addCondiments() {
		System.out.println("adding sugar and milk");
	}
	
	
}
