package com.conpany.project;


import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试继承该类即可
 */

@Transactional
@Rollback
public abstract class Tester {}



