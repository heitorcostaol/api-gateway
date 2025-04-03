package com.procergs.quarkus.infra.util.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResponse<E> {
    private long total;
    private int page;
    private int pageSize;
    private int pageCount;
    private boolean ascending = true;
    private List<E> data;

    public PaginatedResponse(long total, int page, int pageSize, int pageCount, boolean ascending,
            List<E> data) {
        super();
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.data = data;
    }
}
