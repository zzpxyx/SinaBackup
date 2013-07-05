package main;

import parser.Parser;
import writer.Writer;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//import fetcher.Fetcher;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // Fetcher fetcher = new Fetcher();
        // fetcher.login();
        // fetcher.getNextBlog();
        Parser parser = new Parser();
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage blogPage = webClient.getPage("file:///tmp/a.html");
        Writer writer = new Writer();
        writer.write(parser.parse(blogPage));
        writer.close();
    }
}
