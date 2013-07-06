package main;

import parser.Parser;
import writer.Writer;
import checker.Checker;
import fetcher.Fetcher;

public class Main
{
    private static String blogFileName = "blogs.txt";

    public static void main(String[] args) throws Exception
    {
        Checker checker = new Checker(blogFileName);
        Fetcher fetcher = new Fetcher();
        Parser parser = new Parser();
        Writer writer = new Writer(blogFileName);
        fetcher.setLastBlogDateTime(checker.getLastBlogDateTime());
        fetcher.login();
        while (fetcher.isNextBlogAvailable())
        {
            writer.write(parser.parse(fetcher.getNextBlog()));
        }
        writer.close();
    }
}
