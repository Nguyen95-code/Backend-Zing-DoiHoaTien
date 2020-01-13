package com.codegym.zing.controller;

import com.codegym.zing.model.Comment;
import com.codegym.zing.model.User;
import com.codegym.zing.service.CommentService;
import com.codegym.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/users/{user_id}")
@RestController
public class CommentUserRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurent")
    public User getUserCurent() {
        return userService.getCurrentUser();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> listCommentUser(@PathVariable Long user_id){
        User user = userService.findById(user_id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = commentService.findAllByUser(user);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long user_id){
        comment.setUser(getUserCurent());
        User user = userService.findById(user_id);
        comment.setUser(user);
        commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PutMapping("/comments")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long user_id) {
        User user = userService.findById(user_id);
        comment.setUser(user);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable Long user_id) {
        User user = userService.findById(user_id);
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
