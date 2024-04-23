package com.study.springboot.customer.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository  extends JpaRepository<QnA,Long> {
  QnA findByQnaPw(String qnaPw);

  @Query("SELECT q FROM QnA q WHERE q.qnaTitle LIKE %:keyword%")
  List<QnA> findByTitleSearch(@Param("keyword") String keyword);

  @Query("SELECT q FROM QnA q WHERE q.qnaContent LIKE %:keyword%")
  List<QnA> findByContentSearch(@Param("keyword") String keyword);

  @Query("SELECT q FROM QnA q WHERE q.qnaName LIKE %:keyword%")
  List<QnA> findByAuthorSearch(@Param("keyword") String keyword);
}
