class ArithmeticOperation{
	
	public static void main(String[] args)
	{
		if (args.length != 3)
			System.out.println("Wrong entry should be in form of x operand y");
		else
		{
			switch (args[1])
			{
				case "+":
					System.out.println(Integer.valueOf(args[0])+Integer.valueOf(args[2]));
					break;
				case "-":
					System.out.println(Integer.valueOf(args[0])-Integer.valueOf(args[2]));
					break;
				case "*":
					System.out.println(Integer.valueOf(args[0])*Integer.valueOf(args[2]));
					break;
				case "/":
					System.out.println(Integer.valueOf(args[0])/Integer.valueOf(args[2]));
					break;
				default:
					System.out.println("Input must be in the form of 'x operand y' ");
			}
		}
	}
}