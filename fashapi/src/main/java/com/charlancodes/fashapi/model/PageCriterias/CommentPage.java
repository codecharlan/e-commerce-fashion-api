package com.charlancodes.fashapi.model.PageCriterias;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class CommentPage {
    private int pageNumber = 0;
    private int pageSize = 2;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "blogPost";
}
