package com.study.springboot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResDto {
  String status;
  String message;
}
