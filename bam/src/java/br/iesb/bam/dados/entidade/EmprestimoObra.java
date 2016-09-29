package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author jacob.santana
 */
@Entity
@Table(name = "tb_obra_emprestimo", schema = "bam")
public class EmprestimoObra implements Serializable {

    @EmbeddedId
    private EmprestimoObraPK pk;

    public EmprestimoObra() {
    }

    public EmprestimoObra(EmprestimoObraPK pk) {
        this.pk = pk;
    }

    public EmprestimoObraPK getPk() {
        return pk;
    }

    public void setPk(EmprestimoObraPK pk) {
        this.pk = pk;
    }
}
