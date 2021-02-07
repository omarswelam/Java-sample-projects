class StarLeft{
	
	public static void main(String[] args)
	{
		long x=50;
		if (x>0 && x<51)
		{
			for(long i=0; i<x ; i++)
			{
				for(long j=0; j <= i; j++)
				{
					System.out.print("* ");

				}
				System.out.print('\n');
			}
		}
		else
			System.out.print("x should be more than 0 and less than 51");
	}
	
}