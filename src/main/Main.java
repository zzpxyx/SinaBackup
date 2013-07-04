package main;

import fetcher.Fetcher;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Fetcher fetcher=new Fetcher();
		for (int a=0;a<50;a++)
		{
		    System.out.println(fetcher.getNextBlog());
		}
	}
}
