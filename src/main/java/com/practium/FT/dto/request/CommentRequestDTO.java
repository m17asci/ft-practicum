package com.practium.FT.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDTO {

    Long id;
    @NotBlank(message = "Comment is mandatory ")
    @Column(columnDefinition = "text",length = 500)
    String Comment;
    @NotBlank(message = "Comment Date is mandatory")
    LocalDate commentDate;
}
