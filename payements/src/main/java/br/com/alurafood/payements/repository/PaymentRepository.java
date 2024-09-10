package br.com.alurafood.payements.repository;

import br.com.alurafood.payements.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
