package com.practium.FT.service;

import com.practium.FT.dto.request.CommentRequestDTO;
import com.practium.FT.dto.response.CommentResponseDTO;
import com.practium.FT.entity.Comment;
import com.practium.FT.entity.Product;
import com.practium.FT.entity.User;
import com.practium.FT.exception.NotFoundException;
import com.practium.FT.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Cacheable(value = "comment")
    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public CommentResponseDTO findComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Ürün Bulunamadı"));
        return modelMapper.map(comment, CommentResponseDTO.class);

    }

    public List<CommentResponseDTO> findCommentByUserId(Long userId) {
        User user = userService.findById(userId);
        if (userId == null) {
            throw new NotFoundException("Kullanıcı Bulunamadı");
        }
        List<Comment> comments = commentRepository.findByUser(user);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDTOS.add(modelMapper.map(comment, CommentResponseDTO.class));
        }
        return commentResponseDTOS;

    }

    public List<CommentResponseDTO> findCommentByProductId(Long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            throw new NotFoundException("Ürün Bulunamadı");
        }
        List<Comment> comments = commentRepository.findByProduct(product);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            commentResponseDTOS.add(modelMapper.map(comment, CommentResponseDTO.class));
        }
        return commentResponseDTOS;
    }
  @Cacheable(value = "comment")
    public List<CommentResponseDTO> findUserCommentWithDate(Long userId,LocalDate start, LocalDate end) {

        User user = userService.findById(userId);
        if (userId == null) {
            throw new NotFoundException("Kullanıcı Bulunamadı");
        }
        commentRepository.findByUser(user);


        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();

      List<Comment> comments =   commentRepository.findByUserIdAndCommentDateBetween(userId, start, end);
      for (Comment comment : comments){
          commentResponseDTOS.add(modelMapper.map(comment , CommentResponseDTO.class));
      }
       return commentResponseDTOS;
    }


    @Cacheable(value = "comment")
  public List<CommentResponseDTO> findProductCommentWithDate(Long productId,LocalDate start,LocalDate end)  {

        Product product = productService.findById(productId);
        if(productId == null){
            throw new NotFoundException("Ürün Bulunamadı");
        }
        commentRepository.findByProduct(product);


        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
       List<Comment> comments = commentRepository.findByProductIdAndCommentDateBetween(productId,start,end);

        for (Comment comment : comments) {
            commentResponseDTOS.add(modelMapper.map(comment, CommentResponseDTO.class));
        }
        return commentResponseDTOS;
    }
    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        return modelMapper.map(commentRepository.save(modelMapper.map(commentRequestDTO, Comment.class)), CommentResponseDTO.class);

    }
     @Transactional
     @CachePut(value = "comment")
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO newCommentRequest) {

        Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Yorum Bulunamadı"));

        oldComment.setComment(newCommentRequest.getComment());
        oldComment.setCommentDate(newCommentRequest.getCommentDate());
        commentRepository.save(oldComment);

        return modelMapper.map(commentRepository.findById(oldComment.getId()), CommentResponseDTO.class);

    }
   @CacheEvict(value = "comment",allEntries = true)
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
