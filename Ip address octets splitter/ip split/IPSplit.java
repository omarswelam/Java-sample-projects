class IPCutter
{
	public static void main(String[] args)
	{
		
		String[] strArr = args[0].split("[.]");
		
		for (int i=0;i<strArr.length; i++)
		{
			System.out.println(strArr[i]);
		}
	}
	
	
}