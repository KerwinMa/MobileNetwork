import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test extends Thread
{
	String[] reg_name;
	Task<?>[] task;
	Object[] result;
	int total_num=0;
	
	public Test()
	{
		reg_name = new String[5];
		task = new Task<?>[5];		
	}
	
	public <T> void submit(String reg_name, T t)
	{
		//test = (Task<?>)t;
		this.reg_name[total_num] = reg_name;
		task[total_num++] = (Task<?>) t;
		//list[total_num++] = new Object[] {reg_name, (Task<?>)t};
	}
	
	public Object[] gogo()
	{
		result = new Object[total_num];
		Thread ha[] = new Thread[5]; 
		
		try
		{
			for(int i=0; i<total_num; i++)
			{
				//final int parameter = (int)list[i][2];
				final int num = i;
				ha[i] = new Thread(new Runnable() {
				//	int digit = parameter;
					//int asdf = num;
					public void run()
					{
						//CCC asdf = new CCC();						
						System.out.println(reg_name[num] + " : " + task[num].execute());// + (String)list[0][0]);
						result[num] = task[num].execute();
					}
				});
				ha[i].start();				
			}
			for(int i=0; i<total_num; i++)
				ha[i].join();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("done!");
		System.out.println(result[0]+"");
		System.out.println(result[1]+"");
		return result;
	}
	
	public Object[] gogo2() throws Exception
	{
		ExecutorService executor = Executors.newFixedThreadPool(total_num); 
		Future<?>[] haa = new Future[total_num];
		result = new Object[total_num];
		
		for(int i=0; i<total_num; i++)
		{
			final int num = i;
			haa[i] = executor.submit(new Callable<Object>()
			{
				@Override
				public Object call() throws Exception
				{
					// TODO Auto-generated method stub
					System.out.println(reg_name[num] + " : " + task[num].execute());// + (String)list[0][0]);
					result[num] = task[num].execute();
					return  task[num].execute();
				}
			});
//			result[i] = haa[i].get();
			System.out.println(reg_name[num] + " : " + haa[i].get());// + (String)list[0][0]);
			System.out.println(reg_name[num] + " : " + result[i]);// + (String)list[0][0]);
		}
		
		System.out.println("done!");
		System.out.println(result[0]+"");
		System.out.println(result[1]+"");
		return result;
	}
}
