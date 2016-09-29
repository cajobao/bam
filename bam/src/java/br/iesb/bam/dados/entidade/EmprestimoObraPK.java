package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author jacob.santana
 */
public class EmprestimoObraPK implements Serializable {

    @Column(name = "id_emprestimo")
    private Integer idEmprestimo;

    @Column(name = "id_obra")
    private Integer idObra;

    public EmprestimoObraPK() {
    }

    public EmprestimoObraPK(Integer idEmprestimo, Integer idObra) {
        this.idEmprestimo = idEmprestimo;
        this.idObra = idObra;
    }
    
    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }
}
