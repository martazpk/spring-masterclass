package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class PaymentsConfiguration {

    @Bean(name = "paymentIdGenerator")
    public PaymentIdGenerator incrementalPaymentIdGenerator(){
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator(){
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository(){
        return new InMemoryPaymentRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository){
        return new FakePaymentService(paymentIdGenerator, paymentRepository);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger(){
        return new PaymentConsoleLogger();
    }

}
