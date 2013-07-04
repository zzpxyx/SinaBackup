package fetcher;

import java.io.Console;
import java.util.LinkedList;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class Fetcher
{
    private WebClient webClient = new WebClient();
    private String uid;
    private boolean blogListScanned = false;
    private LinkedList<String> blogURLList = new LinkedList<String>();

    public String getNextBlog() throws Exception
    {
        if (!blogListScanned)
        {
            scanBlogList();
            System.out.println(blogURLList.size());
            blogListScanned = true;
        }
        if (blogURLList.isEmpty())
        {
            return null;
        } else
        {
            return blogURLList.removeFirst();
        }
    }

    public boolean isNextBlogAvailable()
    {
        return !blogURLList.isEmpty();
    }

    public void login() throws Exception
    {
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage loginPage = webClient.getPage("http://login.sina.com.cn");
        HtmlForm loginForm = loginPage.getForms().get(0);
        HtmlInput username = loginForm.getInputByName("username");
        HtmlInput password = loginForm.getInputByName("password");
        HtmlSubmitInput loginSubmit = (HtmlSubmitInput) loginForm.getByXPath("//*[@id='main_login']/div[2]/div[2]/div/form/div/ul/li[7]/a[1]/input").get(0);
        Console console = System.console();
        if (console == null)
        {
            System.out.println("No console. Program terminated.");
            System.exit(-1);
        }
        username.setValueAttribute(console.readLine("Username: "));
        password.setValueAttribute(new String(console.readPassword("Password: ")));
        loginSubmit.click();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage redirectPage = webClient.getPage("http://i.blog.sina.com.cn/blogprofile/index.php?com=1");
        String blogLink = ((HtmlAnchor) redirectPage.getHtmlElementById("myblog")).getHrefAttribute();
        uid = blogLink.substring(26, 36);
    }

    private void scanBlogList() throws Exception
    {
        HtmlPage blogListPage;
        HtmlDivision blogListDiv;
        int blogListNumber = 1;
        int blogListMaxNumber = 0;
        blogURLList.clear();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        do
        {
            blogListPage = webClient.getPage("http://control.blog.sina.com.cn/admin/article/articleencryption.php?uid=" + uid + "&page=" + blogListNumber);
            // blogListPage = webClient.getPage("file:///tmp/a.html");
            blogListDiv = (HtmlDivision) blogListPage.getByXPath("//*[@id='module_928']/div[2]/div/div[1]").get(0);

            for (DomElement div : blogListDiv.getChildElements())
            {
                blogURLList.add(((HtmlAnchor) div.getByXPath("./p[1]/span[2]/a").get(0)).getHrefAttribute());
            }
            if (blogListMaxNumber == 0)
            {
                String blogListMaxNumberString = ((HtmlSpan) blogListPage.getByXPath("//*[@id='module_928']/div[2]/div/div[2]/ul/span").get(0))
                        .getTextContent();
                blogListMaxNumber = Integer.valueOf(blogListMaxNumberString.substring(1, blogListMaxNumberString.length() - 1));
            }
            blogListNumber++;
        } while (blogListNumber <= blogListMaxNumber);
    }
}
