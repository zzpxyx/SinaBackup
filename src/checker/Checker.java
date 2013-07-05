package checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Checker
{
    private BufferedReader br;

    public Checker(String blogFileName) throws IOException
    {
        br = new BufferedReader(new FileReader(blogFileName));
    }

    public String getLastBlogDateTime() throws IOException
    {
        String line;
        String lastBlogDateTime = null;
        while ((line = br.readLine()) != null)
        {
            if (line.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"))
            {
                lastBlogDateTime = line;
            }
        }
        System.out.println(lastBlogDateTime);
        br.close();
        return lastBlogDateTime;
    }
}
