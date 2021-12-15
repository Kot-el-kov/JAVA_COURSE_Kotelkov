package com.github.kotelkov.pms;

import com.github.kotelkov.pms.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class BaseRepositoryTest extends DatabaseHelper { }