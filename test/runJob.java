
public class runJob
{

	/**
	 * @param <T>
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		Object[] result = new Object[128];
		// TODO Auto-generated method stub
		Test test = new Test();
		
		test.submit("master",  new CCC(0, 1000000));
		test.submit("master2",  new CCC(0, 200));
		//test.submit("Hi2", "CCC", 10);
		//test.submit("Hi3", "CCC", 1000);
		result = test.gogo2();
		
		System.out.println(result[0]+"");
		System.out.println(result[1]+"");
		


	}

}
