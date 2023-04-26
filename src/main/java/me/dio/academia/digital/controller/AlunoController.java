package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoServiceImpl service;

	@PostMapping
	public Aluno create(@Valid @RequestBody AlunoForm form) {
		return service.create(form);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestParam AlunoUpdateForm formUpdate, @PathVariable Long id) {
		service.update(id, formUpdate);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/avaliacoes/{id}")
	public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
		return service.getAllAvaliacaoFisicaId(id);
	}

	@GetMapping // RETORNA A LISTA DE ALUNOS COM A DATA DE NASC.
	public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNacimento) {
		// REQUIRED É FALSE PQ SE A DATA NÃO FOR PASSADA ELE RETORNARÁ UMA LISTA COM
		// TODOS OS
		// ALUNOS (LÓGICA NO METODO GET ALL DA CLASSE ALUNO SERVICE IMPL)
		// O TIPO DA DATA DE NASC É STRING MAS VAI SER TRANSFORMADA EM LOCAL DATE
		// (SERIALIZAÇÃO E DESSERIALIZAÇÃO).
		return service.getAll(dataDeNacimento);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
