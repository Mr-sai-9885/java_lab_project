package training;
import java.util.Scanner;
public class bbb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int bear=s.nextInt();
		int big=s.nextInt();
		int k=0;
		while(bear<=big) {
			bear=bear*3;
			big=big*2;
			k++;
		}
		System.out.println(k);
	}

}
