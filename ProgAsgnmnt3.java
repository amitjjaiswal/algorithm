import java.io.*;

class ProgAsgnmnt3
{

	int count;

	int choosePivotStart(int arr[],int l,int r)
	{
		//System.out.println("l="+l+" r="+r+" arr[l]="+arr[l]+" arr[r]="+arr[r]);
		return(arr[l]);
	}

	int choosePivotEnd(int arr[],int l,int r)
	{
		//System.out.println("before l="+l+" r="+r+" arr[l]="+arr[l]+" arr[r]="+arr[r]);
		int temp;
		temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
		//System.out.println("later l="+l+" r="+r+" arr[l]="+arr[l]+" arr[r]="+arr[r]);
		return(arr[l]);
	}

	int choosePivotMedian(int arr[],int l,int r)
	{
		int a[] = new int[3];
		int i,j,temp,min,minind,mind;
		a[0]=arr[l];
		a[1]=arr[(l+r)/2];
		a[2]=arr[r];
		
		for(i=0;i<3;i++)
		{
			min=a[i];
			minind=i;
			for(j=i+1;j<3;j++)
			{
				if(a[j]<min)
				{
					min=a[j];
					minind=j;
				}
			}
			temp=a[i];
			a[i]=a[minind];
			a[minind]=temp;
		}
		
		mind=l;
		if(a[1]==arr[r])
			mind=r;
		else if(a[1]==arr[(l+r)/2])
			mind=(l+r)/2;
		temp=arr[l];
		arr[l]=arr[mind];
		arr[mind]=temp;		
		
		//System.out.println("l="+l+" r="+r+" "+a[0]+ " " + a[1] + " " + a[2]);

		return(a[1]);
		
	}

	int partition(int arr[],int l,int r,int p)
	{
		int i,j,temp;
		count = count + (r-l);
		i = l+1;
		for(j=l+1;j<=r;j++)
		{
			if(arr[j]<=p)
			{
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
			}
		}
		
		temp = arr[l];
		arr[l] = arr[i-1];
		arr[i-1] = temp;
		
		return(i-1);
		
	}



	void quickSort(int arr[],int l,int r)
	{
		int n,p,lp;
		n = r-l+1;
		if(n<=1)
			return;
		p = choosePivotMedian(arr,l,r);
		lp = partition(arr,l,r,p);
		//System.out.println("p="+p+" lp="+lp+" arr[lp]="+arr[lp]);
		quickSort(arr,l,lp-1);
		quickSort(arr,lp+1,r);		
	}

	public static void main(String args[])
	{
		try
		{
 	 		FileInputStream fstream = new FileInputStream("/home/ronvj/Desktop/coursera/algo/asgnmnts/quicksort_ip");
			DataInputStream in = new DataInputStream(fstream);
  			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			ProgAsgnmnt3 obj = new ProgAsgnmnt3();
  			String str;
			int i,n;
			//long ans;
			int arr[] = new int[10000];
			
			i=0;
  			while ((str = br.readLine()) != null)
			{
				arr[i]=Integer.parseInt(str);
  				//System.out.println (arr[i]);
				i++;
  			}

			obj.count=0;
			
			n=i;
			//System.out.println ("n="+n+" sorted array:");
			obj.quickSort(arr,0,n-1);
			//System.out.println ("Sorted array:");
			//for(i=0;i<n;i++)
			//	System.out.println (arr[i]);
			System.out.println ("Number of comparisons:" + obj.count);
			
  			in.close();
    		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
	 	}
	}
}
