
public class calculateIndividualReturn 
{	
	private double cost;
	private double taxes;
	private double income;
	
	
	
	public calculateIndividualReturn()
	{
		
	}
	
	public double calculate(double cost, double taxes, double income)
	{
		this.cost = cost;
		this.taxes = taxes;
		this.income = income;
		
		return ((income - taxes)/cost);
	}
}
