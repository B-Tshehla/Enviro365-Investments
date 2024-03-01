package com.enviro.assessment.grad001.boitumelotshehla.service.specification;

import com.enviro.assessment.grad001.boitumelotshehla.dto.NoticeExportRequestDto;
import com.enviro.assessment.grad001.boitumelotshehla.model.WithdrawalNotice;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WithdrawalNoticeSpecification {

    public static Specification<WithdrawalNotice> getNoticesByProductAndDateRange(NoticeExportRequestDto noticeExportRequestDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (noticeExportRequestDto.getProductId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("product").get("id"), noticeExportRequestDto.getProductId()));
            }
            if (noticeExportRequestDto.getStartDate() != null && noticeExportRequestDto.getEndDate() != null) {
                LocalDateTime startDateTime = noticeExportRequestDto.getStartDate().atStartOfDay();
                LocalDateTime endDateTime = noticeExportRequestDto.getEndDate().atTime(LocalTime.MAX);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("withdrawalDate"), startDateTime, endDateTime));
            }
            return predicate;
        };

    }
}
