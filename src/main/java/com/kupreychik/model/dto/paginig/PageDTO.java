package com.kupreychik.model.dto.paginig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PageDTO<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
    private int pageSize;
    private int pageNumber;
    private SortDTO sort;

    public PageDTO(Page<T> page, Pageable pageable) {
        this.content = page.getContent();
        this.pageSize = pageable.getPageSize();
        this.pageNumber = pageable.getPageNumber();
        this.sort = new SortDTO(pageable.getSort());
        this.totalElements = (int) page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public PageDTO(List<T> result, Pageable pageable, Long totalElements) {
        this.content = result;
        this.pageSize = pageable.getPageSize();
        this.pageNumber = pageable.getPageNumber();
        this.sort = new SortDTO(pageable.getSort());
        this.totalElements = totalElements.intValue();
        this.totalPages = totalElements.intValue() / pageable.getPageSize();
    }
}
