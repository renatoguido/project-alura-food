package br.com.alurafood.payements.service;

import br.com.alurafood.payements.dto.PaymentDto;
import br.com.alurafood.payements.model.Payment;
import br.com.alurafood.payements.model.Status;
import br.com.alurafood.payements.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public Page <PaymentDto> findAll(Pageable pageable){
        return repository.findAll(pageable)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto getById(Long id){
        var payment = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto createPayment (PaymentDto dto){
        var payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CRIADO);
        repository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto updatePayment(Long id, PaymentDto dto){
        var payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deletePayment(Long id){
        repository.deleteById(id);
    }
}
