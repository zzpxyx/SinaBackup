package parser;

import main.Blog;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

public class Parser
{
    public Blog parse(HtmlPage blogPage)
    {
        Blog blog = new Blog();
        blog.title = ((HtmlHeading2) blogPage.getByXPath("//*[@id='articlebody']/div[1]/h2").get(0)).getTextContent();
        System.out.println("Parsing blog: " + blog.title);
        blog.dateTime = ((HtmlSpan) blogPage.getByXPath("//*[@id='articlebody']/div[1]/span[2]").get(0)).getTextContent();
        blog.dateTime = blog.dateTime.substring(1, blog.dateTime.length() - 1);
        blog.body = ((HtmlDivision) blogPage.getByXPath("//*[@id='articlebody']/div[3]").get(0)).asText();
        blog.body = blog.body.replaceAll("\n ", "\n");
        blog.body = blog.body + "\n";
        blog.body = blog.body.replaceAll("\n+", "\r\n\t");
        blog.body = "\t" + blog.body;
        blog.body = blog.body.substring(0, blog.body.length() - 3);
        return blog;
    }
}
