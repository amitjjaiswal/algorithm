import java.io.*;
import java.util.*;

class ProgAsgnmnt2
{
		
	long find_inv(int arr[],int s,int e)
	{
		//System.out.println ("s="+s+" e="+e);
		int n,max;
		max=100001;
		n=e-s+1;
		if(n<=1)			//[s=1 e=3] n=3 s+n/2-1 
		{
			//System.out.println ("arr:"+arr[s]);
			return(0);
		}
		
		long l,r,count;
		int lc,rc,ne,i;
		int larr[] = new int[n/2+1];
		int rarr[] = new int[n/2+2];

		l=find_inv(arr,s,s+n/2-1);
		r=find_inv(arr,s+n/2,e);
		
		//System.out.println ("n="+n+" ["+s+","+(s+n/2-1)+"]"+"   ["+(s+n/2)+","+e+"]");
		//System.out.print ("larr:");
		for(i=s;i<=(s+n/2-1);i++)
		{
			larr[i-s]=arr[i];
			//System.out.print (larr[i-s]+" ");
		}
		larr[i-s]=max;
		//System.out.println ();
		//System.out.print ("rarr:");
		for(i=(s+n/2);i<=e;i++)
		{
			rarr[i-(s+n/2)]=arr[i];
			//System.out.print (rarr[i-(s+n/2)]+" ");
		}
		//System.out.println ();
		rarr[i-(s+n/2)]=max;
		
		//System.out.print ("arr:");
		lc=0;
		rc=0;
		count=0L;
		ne=n/2;
		for(i=s;i<=e;i++)
		{
			if(larr[lc]<=rarr[rc])
			{	
				arr[i]=larr[lc];		
				lc++;		
				ne--;
			}
			else
			{
				arr[i]=rarr[rc];
				rc++;	
				count=count+ne;
			}
			//System.out.print(arr[i]+" ");
		}
		//System.out.println();
		return(count+l+r);
	}

	public static void main(String args[])
  	{
  		try
		{
 	 		FileInputStream fstream = new FileInputStream("/home/ronvj/Desktop/coursera/algo/asgnmnts/integer_array");
			DataInputStream in = new DataInputStream(fstream);
  			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			ProgAsgnmnt2 obj = new ProgAsgnmnt2();
  			String str;
			int i,n;
			long ans;
			int arr[] = new int[100001];
			
			i=0;
  			while ((str = br.readLine()) != null)
			{
				arr[i]=Integer.parseInt(str);
  				//System.out.println (arr[i]);
				i++;
  			}
			n=i;
			//System.out.println ("n="+n+" sorted array:");
			ans=obj.find_inv(arr,0,n-1);
			//System.out.println ("I'm returned");
			//for(i=0;i<n;i++)
			//	System.out.println (arr[i]);
			System.out.println (ans);
  			in.close();
    		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
	 	}
	}
}
