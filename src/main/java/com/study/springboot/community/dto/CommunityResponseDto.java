package com.study.springboot.community.dto;

import com.study.springboot.community.Community;
import com.study.springboot.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommunityResponseDto {
  private Long noticeIdx;
  private String noticeTitle;
  private String noticeContent;
  private String noticeMemberId;
  private LocalDateTime noticeDate;

  public CommunityResponseDto(Community entity) {
    this.noticeIdx = entity.getNoticeIdx();
    this.noticeTitle = entity.getNoticeTitle();
    this.noticeContent = entity.getNoticeContent();
    this.noticeMemberId = entity.getNoticeMemberId();
    this.noticeDate = entity.getNoticeDate();
  }

}
