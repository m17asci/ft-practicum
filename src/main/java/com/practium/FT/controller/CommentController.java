package com.practium.FT.controller;

import com.practium.FT.dto.request.CommentRequestDTO;
import com.practium.FT.dto.response.CommentResponseDTO;
import com.practium.FT.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO>findCommentWithCommentId(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.findComment(commentId));
    }
    @GetMapping("/user")
    public ResponseEntity<List<CommentResponseDTO>>findCommentWithUserId(@RequestParam Long userId){
        return ResponseEntity.ok(commentService.findCommentByUserId(userId));
    }

    @GetMapping("/product")
    public ResponseEntity<List<CommentResponseDTO>>findCommentWithProductId(@RequestParam Long productId){
        return ResponseEntity.ok(commentService.findCommentByProductId(productId));
    }

   @GetMapping("/user/date")
    public ResponseEntity<List<CommentResponseDTO>>findUserCommentWithDate(@RequestParam Long userId){
        return ResponseEntity.ok(commentService.findUserCommentWithDate(userId));
    }

    @GetMapping("/product/date")
    public ResponseEntity<List<CommentResponseDTO>>findProductCommentWithDate(@RequestParam Long productId){
        return ResponseEntity.ok(commentService.findProductCommentWithDate(productId));
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO>createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        return ResponseEntity.ok(commentService.createComment(commentRequestDTO));
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO>updateComment(@RequestBody CommentRequestDTO newCommentRequest){
        return ResponseEntity.ok(commentService.updateComment(newCommentRequest));

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void>deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();

    }

}

