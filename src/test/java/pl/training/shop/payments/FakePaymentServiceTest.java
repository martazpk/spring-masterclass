package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    Payment payment;

    @BeforeEach
    void setUp() {
        FakePaymentService paymentService = new FakePaymentService(paymentIdGenerator);
        when(paymentIdGenerator.getNext()).thenReturn(ID);
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
}