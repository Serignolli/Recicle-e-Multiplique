package projeto.pi.reciclemultiplique.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SuppressWarnings("serial")
@Table(name = "CONTRIBUICAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contribuicao extends AbstractEntity<Long> {

    @Column(name = "produto", nullable = false, length = 35)
    private String produto;

    @Column(name = "peso", nullable = false)
    private double peso;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
