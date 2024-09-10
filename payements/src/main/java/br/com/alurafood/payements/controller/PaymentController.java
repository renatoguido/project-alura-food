package br.com.alurafood.payements.controller;


import br.com.alurafood.payements.dto.PaymentDto;
import br.com.alurafood.payements.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PaymentDto> getAll(@PageableDefault(size = 10)Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto){
        return ResponseEntity.ok(service.updatePayment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDto> delete(@PathVariable @NotNull Long id){
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
