package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.Blog;

public class Writer
{
    private BufferedWriter bw;

    public Writer() throws IOException
    {
        bw = new BufferedWriter(new FileWriter("temp.txt", true));
    }

    public void write(Blog blog) throws IOException
    {
        bw.write(blog.title);
        bw.newLine();
        bw.write(blog.dateTime);
        bw.newLine();
        bw.newLine();
        bw.write(blog.body);
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.flush();
    }

    public void close() throws IOException
    {
        bw.close();
    }
}
