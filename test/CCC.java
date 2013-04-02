public class CCC implements Task<Integer>
{
	private final int min, max;
	
	public CCC(int min, int max)
	{
		this.min = min;
		this.max = max;
	}
	
	public Integer execute()
	{
		return sum(min, max);
	}
	
	public int sum(int min, int max)
	{
		int sum=0;
		for(int t=min; t<max; t++)
			sum+=t;
		
		return sum;
	}	
}
