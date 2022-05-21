package lt.viko.eif.rcepauskas.restservice;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.DataService;
import lt.viko.eif.rcepauskas.restservice.repositories.PostRepository;
import lt.viko.eif.rcepauskas.restservice.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Coordinates the work of multiple repositories
 */
@Component
public class UnitOfWork {
    private Blog blog = DataService.createBlogData();

    public PostRepository posts = new PostRepository(blog);
    public UserRepository users = new UserRepository(blog);

    @Bean
    public static Blog getBlog() {
        return new Blog();
    };
}
