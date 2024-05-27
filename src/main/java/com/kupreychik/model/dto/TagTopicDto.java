package com.kupreychik.model.dto;

import com.kupreychik.model.entity.Tag;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Tag}
 */
@Value
public class TagTopicDto implements Serializable {
    Long id;
    String name;
    Long tagTopicId;
    String tagTopicName;
    String tagTopicColor;
}