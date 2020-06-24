package pl.training.shop.payments;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IncrementalPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\d{10}";
    private final IncrementalPaymentIdGenerator generator = new IncrementalPaymentIdGenerator();

    @Test
    void shouldGenerateValidId() {
        String id = generator.getNext();

        assertTrue(id.matches(ID_FORMAT));
    }

    @Test
    void shouldGenerateIdByIncrementalValue() {
        int id = parseInt(generator.getNext());
        int nextId = parseInt(generator.getNext());

        assertThat(id + 1).isEqualTo(nextId);
    }
}