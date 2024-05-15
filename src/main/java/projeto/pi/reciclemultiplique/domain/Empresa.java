package projeto.pi.reciclemultiplique.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SuppressWarnings("serial")
@Table(name = "EMPRESA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends AbstractEntity<Long> {

	@Column(name = "nomeEmpresa", nullable = false, length = 100)
	private String nomeEmpresa;
	
	@Column(name = "cnpj", nullable = false, unique = true, length = 14)
	private String cnpj;
	
	@OneToOne
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 20)
	private String senha;

}