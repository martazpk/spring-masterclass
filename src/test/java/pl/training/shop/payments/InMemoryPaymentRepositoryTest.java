package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InMemoryPaymentRepositoryTest {

    private static final String SOME_ID = "0000000001";
    private static final Payment PAYMENT = Payment.builder()
            .id(SOME_ID)
            .build();
    ;
    @Mock
    Map<String, Payment> payments;

    @Test
    void shouldSavePayment() {
        InMemoryPaymentRepository repository = new InMemoryPaymentRepository();
        repository.setPayments(payments);

        repository.save(PAYMENT);

        verify(payments).put(SOME_ID, PAYMENT);
    }
}