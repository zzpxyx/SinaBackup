package main;

import fetcher.Fetcher;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Fetcher fetcher = new Fetcher();
        fetcher.login();
        fetcher.getNextBlog();
    }
}
