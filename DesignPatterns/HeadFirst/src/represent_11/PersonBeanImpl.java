package represent_11;

public class PersonBeanImpl implements PersonBean{
	String name;
	String sex;
	int age;
	
	public PersonBeanImpl(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getSex() {
		// TODO Auto-generated method stub
		return sex;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setSex(String sex) {
		// TODO Auto-generated method stub
		this.sex = sex;
	}

	@Override
	public void setAge(int age) {
		// TODO Auto-generated method stub
		this.age = age;
	}

}
