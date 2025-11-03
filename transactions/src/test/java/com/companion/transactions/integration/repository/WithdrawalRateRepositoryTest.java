package com.companion.transactions.integration.repository;

import com.companion.transactions.model.WithdrawalRate;
import com.companion.transactions.repository.WithdrawalRateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class WithdrawalRateRepositoryTest {

    @Autowired
    private WithdrawalRateRepository repo;

    @BeforeEach
    void setUp() {
        WithdrawalRate activeRate = WithdrawalRate.builder()
                .rate(100D)
                .active(true)
                .minAmountV(0)
                .build();

        WithdrawalRate secondActiveRate = WithdrawalRate.builder()
                .rate(90D)
                .active(true)
                .minAmountV(1000)
                .description("Second active rate")
                .build();

        WithdrawalRate inactiveRate = WithdrawalRate.builder()
                .rate(1D)
                .active(false)
                .minAmountV(67)
                .description("Inactive rate")
                .build();

        repo.save(activeRate);
        repo.save(secondActiveRate);
        repo.save(inactiveRate);

        assertEquals(3, repo.count());
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
        assertEquals(0, repo.count());
    }

    @Test
    public void shouldFindAllWithdrawalRates() {
        List<WithdrawalRate> rates = repo.findAll();
        assertEquals(3, rates.size());
        assertEquals(100D, rates.getFirst().getRate());
        assertEquals(1000, rates.get(1).getMinAmountV());
        assertEquals("Inactive rate", rates.getLast().getDescription());
    }

    @Test
    public void shouldFindAllActiveWithdrawalRates() {
        List<WithdrawalRate> rates = repo.findAllByActiveTrue();
        assertEquals(2, rates.size());
        assertAll(
                () -> assertTrue(rates.getFirst().isActive()),
                () -> assertTrue(rates.get(1).isActive())
        );
    }

    @Test
    public void shouldFindAllInactiveWithdrawalRates() {
        List<WithdrawalRate> rates = repo.findAllByActiveFalse();
        assertEquals(1, rates.size());
        assertFalse(rates.getFirst().isActive());
    }

    @Test
    public void shouldUpdateWithdrawalRate() {
        UUID id = repo.findAll().getFirst().getId();
        WithdrawalRate rate = repo.findById(id).orElseThrow();
        rate.setActive(false);
        repo.save(rate);
        WithdrawalRate updatedRate = repo.findById(id).orElseThrow();
        assertFalse(updatedRate.isActive());
    }

    @Test
    public void descriptionShouldBeNullByDefault() {
        WithdrawalRate rate = repo.findAll().getFirst();
        assertNull(rate.getDescription());
    }
}