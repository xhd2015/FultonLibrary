package fulton.util.java;

import java.util.ArrayList;

public class UtilArrayList<E> extends ArrayList<E>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6994372275097195424L;

	public UtilArrayList<E> a(E...e)
	{
		for(E e2:e)
		{
			this.add(e2);
		}
		return this;
	}
	
	
}
