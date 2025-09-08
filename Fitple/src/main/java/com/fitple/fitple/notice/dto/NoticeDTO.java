package com.fitple.fitple.notice.dto;

import com.fitple.fitple.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long id;              // DB PK
    private Integer noticeNo;     // 화면 표시용 공지번호 (리스트에서 카운트해서 세팅)
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String adminName; // 목록/상세에서 관리자의 이름 출력용

    public static NoticeDTO toDTO(Notice notice){
        return NoticeDTO.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .viewCount(notice.getViewCount())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .adminName(
                        notice.getAdmin() != null ? notice.getAdmin().getNickname() : null
                )
                .build();
    }

    public Notice toEntity(){
        return Notice.builder()
                // id는 새로 저장할 때 null이어야 자동 증가가 됨
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .viewCount(this.viewCount)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
