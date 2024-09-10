package br.com.alurafood.payements.repository;

import br.com.alurafood.payements.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepositoy extends JpaRepository<Pagamento, Long> {
}
