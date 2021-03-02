package com.ldce.Model.FeeRefund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeeRefundDetailsRepository extends JpaRepository<FeeRefundDetails, Long> {
	@Query("from FeeRefundDetails where fee_refund_enrollment=?1")
    FeeRefundDetails findByEnrollment(String enrollment);
}
