package projeto.pi.reciclemultiplique.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name = "USUARIO")
public class Usuario extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, length = 35)
	private String nome;
	
	@Column(name = "sobrenome", nullable = false, length = 60)
	private String sobrenome;
	
	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private Integer cpf;
	
	@OneToOne
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 20)
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
