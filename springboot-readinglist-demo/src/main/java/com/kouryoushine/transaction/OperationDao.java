package com.kouryoushine.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 自动拥有增删改查
 */
public interface OperationDao extends JpaRepository<OperationLog,Long> {
}
