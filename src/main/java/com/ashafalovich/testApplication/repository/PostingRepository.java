package com.ashafalovich.testApplication.repository;

import com.ashafalovich.testApplication.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findAllByAuthorizedDelivery(boolean isActive);

    List<Posting> findAllByPstngDateBefore(LocalDate date);
    List<Posting> findAllByPstngDateBeforeAndAuthorizedDelivery(LocalDate startDate,boolean isActive);

    List<Posting> findAllByPstngDateBetween(LocalDate startDate,LocalDate endDate);
    List<Posting> findAllByPstngDateBetweenAndAuthorizedDelivery(LocalDate startDate,LocalDate endDate,boolean isActive);

    List<Posting> findAllByPstngDateAfter(LocalDate date);
    List<Posting> findAllByPstngDateAfterAndAuthorizedDelivery(LocalDate date, boolean isActive);




}
