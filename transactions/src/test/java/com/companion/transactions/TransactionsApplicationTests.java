package com.companion.transactions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners(value = {TransactionsApplicationTests.DataSourceUrlPrinter.class},
                        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class TransactionsApplicationTests {

	@Autowired
	private Environment env;

	@Test
	void contextLoads() {
		System.out.println("\n[TEST] spring.datasource.url: " + env.getProperty("spring.datasource.url"));
	}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldUseRealPostgresDatabase() throws SQLException {
        Assertions.assertNotNull(jdbcTemplate.getDataSource());
        String url = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();

        assertEquals(url, env.getProperty("spring.datasource.url")); // or whatever your DB name is
    }

	// Listener pour afficher l'URL au début de tous les tests
	public static class DataSourceUrlPrinter implements TestExecutionListener {
		@Override
		public void beforeTestClass(TestContext testContext) {
			Environment env = testContext.getApplicationContext().getEnvironment();
			System.out.println("\n========================================");
			System.out.println("TEST ENVIRONMENT - Database Configuration");
			System.out.println("========================================");
			System.out.println("spring.datasource.url: " + env.getProperty("spring.datasource.url"));
			System.out.println("========================================\n");
		}
	}

}
