package pl.training.shop.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.training.shop.payments.PaymentStatus.CONFIRMED;
import static pl.training.shop.payments.PaymentStatus.FAILED;

@DataJpaTest //run only unnecessary
@ExtendWith(SpringExtension.class)
class PaymentRepositoryTest {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TestEntityManager testEntityManager;

    private final Payment firstPayment = Payment.builder()
            .id(UUID.randomUUID().toString())
            .money(LocalMoney.of(1000))
            .status(CONFIRMED)
            .timeStamp(Instant.now())
            .build();

    private final Payment secondPayment = Payment.builder()
            .id(UUID.randomUUID().toString())
            .money(LocalMoney.of(1000))
            .status(FAILED)
            .timeStamp(Instant.now())
            .build();

    @BeforeEach
    void setUp() {
        testEntityManager.persist(firstPayment);
        testEntityManager.persist(secondPayment);
        testEntityManager.flush();
    }

    @Test
    void shouldReturnAllConfirmedPayments() {
        List<Payment> result = paymentRepository.findAllByStatus(CONFIRMED);
        assertThat(result)
                .hasSize(1)
                .contains(firstPayment);
    }
}