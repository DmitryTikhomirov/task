package com.kupreychik.conrtoller.rest;

import com.kupreychik.model.dto.TagDto;
import com.kupreychik.model.dto.paginig.PageDTO;
import com.kupreychik.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kupreychik.consts.WebConsts.API;

@RestController
@RequestMapping(API + "/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<PageDTO<TagDto>> getTags(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(tagService.findAll(pageable), HttpStatus.OK);
    }
}
