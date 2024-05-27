package com.kupreychik.model.dto;

import com.kupreychik.model.entity.TaskComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO for {@link TaskComment}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskCommentDto implements Serializable {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;

}