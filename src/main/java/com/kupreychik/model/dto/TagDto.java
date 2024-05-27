package com.kupreychik.model.dto;

import com.kupreychik.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagDto implements Serializable, IsDTO {
    Long id;
    String name;
}