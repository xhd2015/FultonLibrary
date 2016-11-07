package fulton.util.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一些实用方法, 包括print, getInput
 * @author x
 *
 */
public class Util {
	public static void print(Object...o)
	{
		printe("\n",o);
	}
	/**
	 * print something ending with $end
	 * @param end
	 * @param o
	 */
	//@end default is "\n"
	public static void printe(String end,Object...o)
	{
		for(Object i:o)
		{
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print(end);
	}
	//@prompt default is ""
	public static String getInput(String prompt) throws IOException
	{
		//actually I do not mind the efficient
		BufferedReader input = new 	BufferedReader(new InputStreamReader(System.in));
		
		printe("",prompt);
		
		
		return input.readLine();
		
	}
	public static String getInput() throws IOException
	{
		return getInput("");
	}
}
