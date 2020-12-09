package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FakePaymentServiceSpringTest {

    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();
    private static final String ID = "1";

    @MockBean
    PaymentIdGenerator paymentIdGenerator;
    @MockBean
    PaymentRepository repository;
    @MockBean
    ApplicationEventPublisher publisher;
    @Autowired
    PaymentService paymentService;
    Payment payment;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository,
                                             ApplicationEventPublisher eventPublisher) {
            return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
        }
    }

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(ID);
        when(repository.save(any(Payment.class))).thenReturn(payment);
        payment = paymentService.process(REQUEST);
    }

    @Test
    void shouldAssignIdToPayment() {
        assertThat(payment.getId()).isEqualTo(ID);
    }

    @Test
    void shouldAssignMoneyToPayment() {
        assertThat(payment.getMoney()).isEqualTo(MONEY);
    }

    @Test
    void shouldAssignTimeToPayment() {
        assertThat(payment.getTimeStamp()).isNotNull();
    }

    @Test
    void shouldAssignStartedStatus() {
        assertThat(payment.getStatus()).isEqualTo(PaymentStatus.STARTED);
    }

    @Test
    void shouldSavePayment() {
        verify(repository).save(payment);
    }
}