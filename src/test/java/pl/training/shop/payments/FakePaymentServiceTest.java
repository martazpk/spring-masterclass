package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FakePaymentServiceTest {

    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();
    private static final String ID = "1";

    @Mock
    PaymentIdGenerator paymentIdGenerator;
    @Mock
    PaymentRepository repository;
    @Mock
    ApplicationEventPublisher publisher;
    Payment payment;

    @BeforeEach
    void setUp() {
        FakePaymentService paymentService = new FakePaymentService(paymentIdGenerator, repository, publisher);
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