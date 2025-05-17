package week3;

public class contructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		employee e=new employee(100,"kumar",23);
	      e.display();
		

	}

}
class employee
{
  int sal=10000;
  String name="sai";
  double roll=2879;
  employee(int a,String n,double r)
  {
	 
	  name=n;
	  roll=r;
  }
  void display()
  {
	  System.out.println(sal);
		System.out.println(name);
		System.out.println(roll);
  }
}
