package me.dio.academia.digital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

//@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_avaliacoes")
public class AvaliacaoFisica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL) // MUDANÇAS EM AVALIAÇÃO FISÍCA TERÃO O EFIETO CASCATA (AFETARÃO TUDO
											// RELACIONADO A ALUNO)
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	private LocalDateTime dataDaAvaliacao = LocalDateTime.now();

	@Column(name = "peso_atual")
	private double peso;

	@Column(name = "altura_atual")
	private double altura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getDataDaAvaliacao() {
		return dataDaAvaliacao;
	}

	public void setDataDaAvaliacao(LocalDateTime dataDaAvaliacao) {
		this.dataDaAvaliacao = dataDaAvaliacao;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "AvaliacaoFisica [id=" + id + ", aluno=" + aluno + ", dataDaAvaliacao=" + dataDaAvaliacao + ", peso="
				+ peso + ", altura=" + altura + "]";
	}

}
