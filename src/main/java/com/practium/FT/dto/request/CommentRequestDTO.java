package com.practium.FT.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDTO {

    @NotBlank(message = "Comment is mandatory ")
    @Column(columnDefinition = "text",length = 500)
    String Comment;
    @NotBlank(message = "Comment Date is mandatory")
    LocalDate commentDate;
}
