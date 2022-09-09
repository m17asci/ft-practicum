package com.practium.FT.repository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.practium.FT.entity.Comment;
import com.practium.FT.entity.Product;
import com.practium.FT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByProduct(Product product);

    List<Comment> findByUser(User user);



    List<Comment> findByUserIdAndCommentDateBetween(Long userId ,LocalDate start, LocalDate end);
    List<Comment> findByProductIdAndCommentDateBetween(Long productId,LocalDate start,LocalDate end);




}
