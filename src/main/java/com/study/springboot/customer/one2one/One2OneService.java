package com.study.springboot.customer.one2one;

import com.study.springboot.customer.one2one.dto.One2OneSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class One2OneService {
  private final One2OneRepository one2OneRepository;

  @Transactional
  public Long save(final One2OneSaveRequestDto dto){
    One2One entity = one2OneRepository.save(dto.toEntity());
    return entity.getOne2oneIdx();
  }
  @Transactional(readOnly = true)
  public boolean existsById(Long noticeIdx){
    boolean isFound = one2OneRepository.existsById(noticeIdx);
    return isFound;
  }


}
