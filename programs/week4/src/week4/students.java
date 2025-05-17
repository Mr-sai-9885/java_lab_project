package week4;

public class students {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		student s1=new student(24,"sai",1);
		student s2=new student(25,"shiva",2);
		student s3=new student(26,"kumar",3);
		s1.display();
		System.out.println("-------------------");
		s2.display();
		System.out.println("-------------------");
		s3.display();
		

	}

}
class student
{
	int roll;
	String name;
	int age;
	student(int r,String n,int a)
	{
    roll=r;
    name=n;
    age=a;
    
   }
	void display()
	{
		System.out.println(roll +"\n" +name +"\n" +age);
	}
}