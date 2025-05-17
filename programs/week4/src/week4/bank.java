package week4;
import java.util.Scanner;
public class bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner c= new Scanner(System.in);
		int ac1=c.nextInt();
		int ba1=c.nextInt();
		banks bs=new banks(ac1,ba1);
		bs.dispaly();
		

	}

}
class banks
{
	int ac2;
	double b2;
	bank bk=new bank();
    banks(int ac,double b)
    {
    	ac2=ac;
    	b2=b;
    	if(ac == 0)
    	{
    		System.err.println("message");
    		System.out.println("enter valid");
    		
    	}
    	void display()
    	{
    		System.out.println("a1");
    		System.out.println("________________");
    		System.out.println("b1");
    	}
    	
    }
}
