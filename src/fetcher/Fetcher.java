package fetcher;

import java.io.Console;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class Fetcher
{
	WebClient webClient = new WebClient();
	
	public void login() throws Exception
	{
		HtmlPage loginPage = webClient.getPage("http://login.sina.com.cn");
		HtmlForm loginForm = loginPage.getForms().get(0);
		HtmlInput username = loginForm.getInputByName("username");
		HtmlInput password = loginForm.getInputByName("password");
		HtmlSubmitInput loginSubmit = (HtmlSubmitInput) loginForm.getByXPath(
				"//*[@id='main_login']/div[2]/div[2]/div/form/div/ul/li[7]/a[1]/input").get(0);
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
		String blogLink = ((HtmlAnchor) redirectPage.getElementById("myblog")).getHrefAttribute();
		String uid = blogLink.substring(26, 36);
		HtmlPage blogPage = webClient
				.getPage("http://control.blog.sina.com.cn/blog_rebuild/blog/controllers/articlelist.php?uid=" + uid
						+ "&p=1&status=5");
		// System.out.println(result.getUrl());
	}
}
