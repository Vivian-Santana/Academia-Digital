package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data //ANOTAÇÃO DO LOMBOK PARA ABSTRAIR OS GETTER OS SETTERS O EQUALS E O HASHCODE
@NoArgsConstructor //CONSTRUTOR VAZIO (PARA NÃO DAR ERRO NO HYBERNATE)
@AllArgsConstructor //CONSTRUTOR DE ATRIBUTOS
@Entity //ANOTAÇÃO DE PERSISTENCIA DO BANCO DE DADOS
@Table(name = "tb_alunos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  
  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)//CARREGA OS DADOS DO ALUNO EXCETO AS LISTAS DE AVALIAÇÕES FÍSICAS. 
  																						//SERVE PARA NÃO SOBRECARREGAR A CONSULTA DO BANCO, DEIXANDO MAIS PERFOMÁTICO.
  
  @JsonIgnore //IGNORAR EXCEPTIONS DO JSON
  private  List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

}
