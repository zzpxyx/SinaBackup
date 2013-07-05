package writer;

import main.Blog;

public class Writer
{
    public void write(Blog blog)
    {
        System.out.println(blog.dateTime);
        System.out.println(blog.title + "\n");
        System.out.println(blog.body + "\n");
    }
}
