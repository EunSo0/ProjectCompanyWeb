package com.study.springboot.customer.one2one.dto;

import com.study.springboot.customer.one2one.One2One;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class One2OneResponseDto {
  private Long one2oneIdx;
  private String one2oneName;
  private String one2onePhone;
  private String one2oneEmail;
  private String one2oneAddress;
  private String one2oneTitle;
  private String one2oneContent;
  private LocalDateTime one2oneDate;

  public One2OneResponseDto(One2One entity) {
    this.one2oneIdx = entity.getOne2oneIdx();
    this.one2oneName = entity.getOne2oneName();
    this.one2onePhone = entity.getOne2onePhone();
    this.one2oneEmail = entity.getOne2oneEmail();
    this.one2oneAddress = entity.getOne2oneAddress();
    this.one2oneTitle = entity.getOne2oneTitle();
    this.one2oneContent = entity.getOne2oneContent();
    this.one2oneDate = entity.getOne2oneDate();
  }
}
