package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

  /**
   *
   * @param DATADENASCIMENTO: DATA DE NASCIMENTO DOS ALUNOS
   * @return LISTA COM TODOS OS ALUNOS COM A DATA DE NASCIMENTO PASSADA COMO PARÂMETRO DA FUNÇÃO
   */
  List<Aluno> findByDataDeNascimento(LocalDate dataDeNascimento);

}
