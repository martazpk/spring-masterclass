package pl.training.shop.payments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    List<Payment> findAllByStatus(PaymentStatus status);
}
