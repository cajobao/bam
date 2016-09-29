package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno", schema = "bam")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
public class Aluno extends Pessoa implements Serializable {

    @Column(name = "ds_curso")
    private String dsCurso;

    public Aluno() {
    }

    public String getDsCurso() {
        return dsCurso;
    }

    public void setDsCurso(String dsCurso) {
        this.dsCurso = dsCurso;
    }
}
