package com.study.springboot.customer.qna;

import com.study.springboot.customer.qna.dto.QnAResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnAService {
  private final QnARepository qnARepository;

  @Transactional(readOnly = true)
  public List<QnAResponseDto> findAll(){
    Sort sort = Sort.by(Sort.Direction.DESC, "qnaDate", "qnaIdx");
    List<QnA> list = qnARepository.findAll(sort);

    return list.stream().map(QnAResponseDto::new).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public QnAResponseDto findById(Long qnaIdx){
    QnA entity
        = qnARepository.findById( qnaIdx )
        .orElseThrow( () -> new IllegalArgumentException(
            "없는 글인덱스입니다. qnaIdx:" + qnaIdx));
    return new QnAResponseDto(entity);
  }

  @Transactional(readOnly = true)
  public boolean findByPwd(String pwd){
    QnA entity = qnARepository.findByQnaPw(pwd);
    return entity != null;
  }

  public List<QnAResponseDto> findByTitleSearch(String keyword) {
    List<QnA> qnaList = qnARepository.findByTitleSearch(keyword);
    return qnaList.stream()
        .map(QnAResponseDto::new)
        .collect(Collectors.toList());
  }

  public List<QnAResponseDto> findByContentSearch(String keyword) {
    List<QnA> qnaList = qnARepository.findByContentSearch(keyword);
    return qnaList.stream()
        .map(QnAResponseDto::new)
        .collect(Collectors.toList());
  }

  public List<QnAResponseDto> findByAuthorSearch(String keyword) {
    List<QnA> qnaList = qnARepository.findByAuthorSearch(keyword);
    return qnaList.stream()
        .map(QnAResponseDto::new)
        .collect(Collectors.toList());
  }
}
