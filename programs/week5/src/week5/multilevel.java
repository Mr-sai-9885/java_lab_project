package week5;

public class multilevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			student s=new student();
			s.display();
			s.sdisplay();
			s.mdisplay();

	}

}
class person{
	void display()
	{
		System.out.println("laxman");
	}
	
}
class manager extends person{
	void mdisplay()
	{
		System.out.println("mahesh");
	}
}
class student extends manager
{
	void sdisplay() 
	{
		System.out.println("sai");
	}
	
}
