package projeto.pi.reciclemultiplique.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENDERECOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends AbstractEntity<Long> {
    @Column(nullable = false)
    private String logradouro;
    
    @Column(nullable = false)
    private String bairro;
    
    @Column(nullable = false)
    private String cidade;
    
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;
    
    @Column(nullable = false, length = 9)
    private String cep;
    
    @Column(nullable = false, length = 5)
    private Integer numero;
    
    private String complemento;
    
}