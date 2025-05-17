package training;
import java.util.Scanner;
public class threebrothers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=1,b=2,c=3;
		Scanner s=new Scanner(System.in);
		int come1=s.nextInt();
		int come2=s.nextInt();
		if(a==come1 || a==come2) {
			if(b==come1 || b==come2) {
				System.out.println(c);
			}
		}
		if(b==come1 || b==come2) {
			if(c==come1 || c==come2) {
				System.out.println(a);
			}
		}
		if(c==come1 || c==come2) {
			if(a==come1 || a==come2) {
				System.out.println(b);
			}
		}

	}

}
