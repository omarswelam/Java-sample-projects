class StarBoth{
	
	public static void main(String[] args)
	{
		long x=10;
		if (x>0 && x<21)
		{
			for(long i=0; i<x ; i++)
			{
				for(long j=0; j <= (2*x + i); j++)
				{
					if (j <= i)
						System.out.print("* ");
					else if (j > i && j < x)
						System.out.print("  ");
					else if (j >= (2*x - i))
						System.out.print("* ");
					else
						System.out.print("  ");

				}
				System.out.print('\n');
			}
		}
		else
			System.out.print("x should be more than 0 and less than 21");
	}
	
}