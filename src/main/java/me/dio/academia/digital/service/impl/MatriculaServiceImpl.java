package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private AlunoServiceImpl alunoService;

	@Override
	public Matricula create(MatriculaForm form) {
		Matricula matricula = new Matricula();
		Aluno aluno = alunoService.getById(form.getAlunoId());
		matricula.setAluno(aluno);

		return matriculaRepository.save(matricula);
	}

	@Override
	public Matricula getById(Long id) {
		Optional<Matricula> matricula = matriculaRepository.findById(id);

		return matricula.orElseThrow(() -> new ObjectNotFoundException("Matricula não encontrada.", null));
	}

	@Override
	public List<Matricula> getAll(String bairro) {

		if (bairro == null) {
			return matriculaRepository.findAll();
		} else {
			return matriculaRepository.findAlunosMatriculadosBairro(bairro);
		}

	}

	@Override
	public void delete(Long id) {
		getById(id);
		matriculaRepository.deleteById(id);
	}

}
