package projeto.pi.reciclemultiplique.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name = "EMPRESA")
public class Empresa extends AbstractEntity<Long> {

	@Column(name = "nomeEmpresa", nullable = false, length = 100)
	private String nomeEmpresa;
	
	@Column(name = "cnpj", nullable = false, unique = true, length = 14)
	private Integer cnpj;
	
	@Column(name = "endereco", nullable = false, length =255)
	private String endereco;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 20)
	private String senha;

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
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