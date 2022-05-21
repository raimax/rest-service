package lt.viko.eif.rcepauskas.restservice.controllers;

import lt.viko.eif.rcepauskas.blog.User;
import lt.viko.eif.rcepauskas.restservice.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UsersController {
    private UnitOfWork unitOfWork;

    @Autowired
    public UsersController(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(unitOfWork.users.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = unitOfWork.users.get(id);

        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        unitOfWork.users.insert(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User userToUpdate = unitOfWork.users.get(user.getId());

        if (userToUpdate == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        unitOfWork.users.update(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        User userToDelete = unitOfWork.users.get(id);

        if (userToDelete == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        unitOfWork.users.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
