package checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Checker
{
    private BufferedReader br;
    private boolean fileExists = false;

    public Checker(String blogFileName) throws IOException
    {
        File blogFile = new File(blogFileName);
        if (blogFile.exists())
        {
            br = new BufferedReader(new FileReader(blogFile));
            fileExists = true;
        }
    }

    public String getLastBlogDateTime() throws IOException
    {
        String line;
        String lastBlogDateTime = null;
        if (!fileExists)
        {
            return null;
        }
        while ((line = br.readLine()) != null)
        {
            if (line.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"))
            {
                lastBlogDateTime = line.substring(0, line.length() - 3);
            }
        }
        br.close();
        return lastBlogDateTime;
    }
}
