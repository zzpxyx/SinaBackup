package parser;

import main.Blog;

import com.gargoylesoftware.htmlunit.html.DomElement;
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
        blog.dateTime = ((HtmlSpan) blogPage.getByXPath("//*[@id='articlebody']/div[1]/span[2]").get(0)).getTextContent();
        blog.dateTime = blog.dateTime.substring(1, blog.dateTime.length() - 1);
        HtmlDivision blogBodyDiv = (HtmlDivision) blogPage.getByXPath("//*[@id='articlebody']/div[3]").get(0);
        blog.body = "";
        for (DomElement e : blogBodyDiv.getChildElements())
        {
            blog.body += (e.getTextContent() + "\n");
        }
        blog.body = blog.body.replaceAll("\r", "\n");
        blog.body = blog.body.replaceAll("\n+", "\n");
        blog.body = blog.body.replaceAll("\n", "\r\n\r\n");
        blog.body = blog.body.substring(0, blog.body.length() - 4);
        return blog;
    }
}
