package training;
import java.util.Scanner;
public class police {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int top,n,event,p=0,un=0;
		n=s.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("enter event");
			event=s.nextInt();
			if(event==-1) {
				if(p>0) {
					p--;
				}
				else {
					un++;
				}
			}
			else {
				p=p+event;
			}
		}
		System.out.println("unsolved is:"+un);
		
		
		
	}

}
