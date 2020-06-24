package pl.training.shop.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UUIDPaymentIdGeneratorTest {
    
    private static final String ID_FORMAT = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
    private final UUIDPaymentIdGenerator generator = new UUIDPaymentIdGenerator();

    @Test
    void shouldGenerateValidId() {
        String id = generator.getNext();

        assertTrue(id.matches(ID_FORMAT));
    }
}