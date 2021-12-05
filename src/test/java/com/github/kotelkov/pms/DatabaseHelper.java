package com.github.kotelkov.pms;

import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:drop_all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DatabaseHelper { }
