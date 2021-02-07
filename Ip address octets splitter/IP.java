class IPCutter
{
	public static void main(String[] args)
	{

		int idx;
		int start = 0;
		int counter=0;
		
		while (true)
		{
			idx = args[0].indexOf('.', start);
			if (idx == -1)
			{
				break;
			}
			System.out.println(args[0].substring(start,idx));
			start = idx+1;
			counter++;
		}		
		if (counter==0)
			System.out.println("Incorrect IP format should be xx.xx.xx.xx");
		else
			System.out.println(args[0].substring(start));
	}
	
	
}