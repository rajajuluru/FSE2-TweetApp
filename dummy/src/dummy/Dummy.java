package dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   List<Integer> list = new ArrayList<Integer>();
	        list.add(2);
	        list.add(1);
	        list.add(3);
	        
	        
	        Consumer<List<Integer>> listmodify=list1->{
	        	for(int i=0;i<list.size();i++)
	        	{
	        		list.set(i, list.get(i)*2);
	        	}
	        };
	        
	        
	        Consumer<List<Integer>> iterate=list2->{
	        	for(int i1=0;i1<list.size();i1++)
	        	{
	        		//list.set(i, list.get(i)*2);
	        		System.out.println(list.get(i1));
	        	}
	        };
	      
	        listmodify.andThen(iterate).accept(list); 
	       
	        

	}

}
