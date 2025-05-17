package week5;

class multimanager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		managers m = new managers();
		System.out.println("details of the manager:");
		m.setdetails(312,28000,"MOHAN");
	    m.showdetails();
	    m.getDA();
	    m.setCA();
	    m.getgross();

	}

}
class workerdetails 
{
	int c,s;
	String name;
	float h;
	void setdetails(int id,int sal,String n)
	{
		c=id;
		s=sal;
		name=n;
	}
	void showdetails()
	{
		System.out.println("code is"+c);
		System.out.println("salary is:"+s);
		System.out.println("name is:"+name);
	}
	void getHRA()
	{
		h=(float)s*60/100;
		System.out.println("HRA IS:"+h);
	}
}
class officersal extends workerdetails
{
	float d;
	void getDA()
	{
		d=(float)s*30/100;
		System.out.println("DA is:"+d);
	}
}
class managers extends officersal
{
	float c,g;
	void setCA()
	{
		c=(float)s*98/100;
		System.out.println("CA is:"+c);
	}
	void getgross()
	{
		g=s+h+d+c;
		System.out.println("gross salary is:"+g);
	}
	
}
