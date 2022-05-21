package lt.viko.eif.rcepauskas.restservice.controllers;

import lt.viko.eif.rcepauskas.blog.Post;
import lt.viko.eif.rcepauskas.restservice.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
    private UnitOfWork unitOfWork;

    @Autowired
    public PostsController(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(unitOfWork.posts.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        Post post = unitOfWork.posts.get(id);

        if (post == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        unitOfWork.posts.insert(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<String> updatePost(@RequestBody Post post) {
        Post postToUpdate = unitOfWork.posts.get(post.getId());

        if (postToUpdate == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        unitOfWork.posts.update(post);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Integer id) {
        Post postToDelete = unitOfWork.posts.get(id);

        if (postToDelete == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        unitOfWork.posts.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
