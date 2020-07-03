package com.aubga.mockito.learning;

import org.junit.Before;
import org.mockito.Mock;

public class ArticleManagerTest extends SampleBaseTestCase {

    @Mock private ArticleCalculator calculator;
    @Mock private ArticleDatabase database;
    @Mock private UserProvider userProvider;

    private ArticleManager manager;

    @Before public void setup() {
        manager = new ArticleManager(userProvider, database, calculator);
    }
}