package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_professor", schema = "bam")
public class Professor extends Pessoa implements Serializable {

    @Column(name = "id_pessoa", insertable = false, updatable = false)
    private Integer idProfessor;

    @Column(name = "ds_departamento")
    private String dsDepartamento;

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getDsDepartamento() {
        return dsDepartamento;
    }

    public void setDsDepartamento(String dsDepartamento) {
        this.dsDepartamento = dsDepartamento;
    }
}
