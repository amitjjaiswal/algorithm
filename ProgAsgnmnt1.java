import java.io.*;
import java.util.*;

class ProgAsgnmnt1
{
	String add(String num1,String num2)
	{
		int n1,n2,n,i,c;
		String ans;
		n1=num1.length();
		n2=num2.length();
		while(n1<n2)
		{
			num1="0"+num1;
			n1++;
		}
		while(n2<n1)
		{
			num2="0"+num2;
			n2++;
		}
		n=n1;

		StringBuilder sb = new StringBuilder(num1+num2);

		c=0;
		for(i=n-1;i>=0;i--)
		{
			sb.setCharAt(i,(char)(((num1.charAt(i)-48) + (num2.charAt(i)-48) +c)%10 +48));
			c =  ((num1.charAt(i)-48) + (num2.charAt(i)-48) +c)/10;
		}
		ans=sb.toString();
		ans=ans.substring(0,n);
		if(c==1)
			ans="1"+ans;
		return(ans);
	}

	String sub(String num1,String num2)
	{
		int n1,n2,n,i,c;
		String ans;
		n1=num1.length();
		n2=num2.length();
		while(n1<n2)
		{
			num1="0"+num1;
			n1++;
		}
		while(n2<n1)
		{
			num2="0"+num2;
			n2++;
		}
		n=n1;

		StringBuilder sb = new StringBuilder(num1+num2);

		c=0;
		for(i=n-1;i>=0;i--)
		{
			if(num1.charAt(i)>=(num2.charAt(i)+c))
			{
				sb.setCharAt(i,(char)(num1.charAt(i)-(num2.charAt(i)+c)+48));
				c=0;
			}
			else
			{
				sb.setCharAt(i,(char)(num1.charAt(i)-(num2.charAt(i)+c)+10+48));
				c=1;
			}
		}

		ans=sb.toString();
		ans=ans.substring(0,n);
		
		i=0;
		while(ans.charAt(i)=='0' && i<ans.length()-1)
			i++;
		if(i<ans.length()-1)
			ans=ans.substring(i);	
		
		return(ans);
	}
		

	String karatsuba_mult(String num1,String num2)
	{
		int n1,n2,n,i;
		String a,b,c,d,ac,bd,apb,cpd,apbmcpd,ans;
		n1=num1.length();
		n2=num2.length();
		while(n1<n2)
		{
			num1="0"+num1;
			n1++;
		}
		while(n2<n1)
		{
			num2="0"+num2;
			n2++;
		}
		n=n1;

		//System.out.println("karatsuba num1="+num1+" num2="+num2+" n="+n);

		if(n==1)
			return(Integer.toString((num1.charAt(0)-48)*(num2.charAt(0)-48)));

		a=num1.substring(0,n/2);
		b=num1.substring((n/2));
		c=num2.substring(0,n/2);
		d=num2.substring((n/2));

		//System.out.println("a="+a);
		//System.out.println("b="+b);
		//System.out.println("c="+c);
		//System.out.println("d="+d);

		ac=karatsuba_mult(a,c);
		//System.out.println("ac="+ac);
		bd=karatsuba_mult(b,d);
		//System.out.println("bd="+bd);
		apb=add(a,b);
		//System.out.println("apb="+apb);
		cpd=add(c,d);
		//System.out.println("cpd="+cpd);
		apbmcpd=karatsuba_mult(apb,cpd);
		//System.out.println("apbmcpd="+apbmcpd);
		apbmcpd=sub(apbmcpd,ac);
		apbmcpd=sub(apbmcpd,bd);
		
		if(n%2==1)
			n=n+1;
		i=0;
		while(i<n)
		{
			ac=ac+"0";
			i++;
		}

		i=0;
		while(i<(n/2))
		{
			apbmcpd=apbmcpd+"0";
			i++;
		}
		
		ans=add(add(ac,apbmcpd),bd);

		return(ans);
	}
	public static void main(String args[])
	{
		String num1,num2,ans;
		int i;
		ProgAsgnmnt1 obj = new ProgAsgnmnt1();
		Scanner in = new Scanner(System.in);
		num1=in.nextLine();
		num2=in.nextLine();
		//System.out.println("num1="+num1);
		//System.out.println("num2="+num2);
		ans=obj.karatsuba_mult(num1,num2);

		i=0;
		while(ans.charAt(i)=='0' && i<ans.length()-1)
			i++;
		if(i<ans.length()-1)
			ans=ans.substring(i);

		System.out.println(ans);
	}
}
