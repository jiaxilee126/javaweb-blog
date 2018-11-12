package com.lee.qiniu.dao;

import org.springframework.stereotype.Repository;

import com.lee.qiniu.entity.OperationLog;

@Repository
public interface OperationLogDao {
	void insert(OperationLog operationLog);
	OperationLog getById(Integer id);
}
