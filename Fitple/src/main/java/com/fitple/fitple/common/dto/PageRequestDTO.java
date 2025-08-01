package com.fitple.fitple.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@Data
@Builder
public class PageRequestDTO {

    @Builder.Default
    @Min(1)
    private Integer page = 1;

    @Builder.Default
    @Min(5)
    @Max(100)
    private Integer size = 10;

    private String[] types;
    private String keyword;
    private Integer salary;
    private String location;
    private String ncs;

    // 지역 필터링용 법정시군구코드
    private String[] zipCds;

    private List<String> keywords;


    public String getLink() {
        StringBuilder sb = new StringBuilder();

        if (keyword != null && !keyword.isEmpty()) {
            sb.append("&keyword=").append(keyword);
        }

        if (zipCds != null) {
            for (String zip : zipCds) {
                sb.append("&zipCds=").append(zip);
            }
        }

        return sb.toString();
    }


    public Pageable getPageable(String sortType) {
        return PageRequest.of(getPage()-1, getSize(), Sort.by(sortType).descending());
    }

    public int getPage() {
        return (page == null || page < 1) ? 1 : page;
    }

    public int getSize() {
        return (size == null) ? 10 : size;
    }

    public int getSkip() {
        return (getPage() - 1) * getSize();
    }




}
