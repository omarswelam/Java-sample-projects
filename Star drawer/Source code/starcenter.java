class StarCenter{
	
	public static void main(String[] args)
	{	long jj=1;
		long x=30;
		if (x>0 && x<31)
		{
			for(long i=0; i<x ; i++)
			{
				for(long j=0; j <= (x + i); j++)
				{
					if (j >= (x - i))
					{	if (jj%2 == 1)
							System.out.print("* ");
						else 
							System.out.print("  ");
						
						jj++;
					}
					else
						System.out.print("  ");

				}
				System.out.print('\n');
				jj=1;
			}
		}
		else
			System.out.print("x should be more than 0 and less than 31");
	}
	
}