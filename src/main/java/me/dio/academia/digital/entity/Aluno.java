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

//@Data //ANOTAÇÃO DO LOMBOK PARA ABSTRAIR OS GETTER OS SETTERS O EQUALS E O HASHCODE
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

  public Aluno() {
	  super();
  }
  
  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)//CARREGA OS DADOS DO ALUNO EXCETO AS LISTAS DE AVALIAÇÕES FÍSICAS. 
  																						//SERVE PARA NÃO SOBRECARREGAR A CONSULTA DO BANCO, DEIXANDO MAIS PERFOMÁTICO.
  
  @JsonIgnore //IGNORAR EXCEPTIONS DO JSON
  private  List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public List<AvaliacaoFisica> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoFisica> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avaliacoes, bairro, cpf, dataDeNascimento, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(avaliacoes, other.avaliacoes) && Objects.equals(bairro, other.bairro)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(dataDeNascimento, other.dataDeNascimento)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", bairro=" + bairro + ", dataDeNascimento="
				+ dataDeNascimento + ", avaliacoes=" + avaliacoes + "]";
	}
	
}
