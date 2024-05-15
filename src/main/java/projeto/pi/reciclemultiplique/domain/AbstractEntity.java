package projeto.pi.reciclemultiplique.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private ID Id;

    public ID getId() {
        return Id;
    }

    public void setId(ID Id) {
        this.Id = Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        return Objects.equals(Id, other.Id);
    }
    
    @Override
    public String toString() {
        return "Id = " + Id;
    }
}
