package app.lojaDeRoupa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.lojaDeRoupa.entity.Venda;
import app.lojaDeRoupa.service.VendaService;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Venda venda) {
        try {
            String mensagem = this.vendaService.save(venda);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Venda venda, @PathVariable long id) {
        try {
            String mensagem = this.vendaService.update(venda, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Venda> findById(@PathVariable long id) {
        try {
            Venda venda = this.vendaService.findById(id);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Venda>> findAll() {
        try {
            List<Venda> lista = this.vendaService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            String mensagem = this.vendaService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findByClienteNome")
    public ResponseEntity<List<Venda>> findByClienteNome(@RequestParam String nome) {
        try {
            List<Venda> vendas = vendaService.findByClienteNome(nome);
            return new ResponseEntity<>(vendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByFuncionarioNome")
    public ResponseEntity<List<Venda>> findByFuncionarioNome(@RequestParam String nome) {
        try {
            List<Venda> vendas = vendaService.findByFuncionarioNome(nome);
            return new ResponseEntity<>(vendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Venda>> findTop10ByValorTotal() {
        try {
            List<Venda> vendas = vendaService.findTop10ByValorTotal();
            return new ResponseEntity<>(vendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
