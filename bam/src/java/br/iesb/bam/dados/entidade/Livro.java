package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_livro", schema = "bam")
@PrimaryKeyJoinColumn(name = "id_obra", referencedColumnName = "id_obra")
public class Livro extends Obra implements Serializable {

    @Column(name = "ds_ibsn")
    private String dsIbsn;

    @Column(name = "st_referencia")
    private String stReferencia;

    public Livro() {
    }

    public String getDsIbsn() {
        return dsIbsn;
    }

    public void setDsIbsn(String dsIbsn) {
        this.dsIbsn = dsIbsn;
    }

    public String getStReferencia() {
        return stReferencia;
    }

    public void setStReferencia(String stReferencia) {
        this.stReferencia = stReferencia;
    }
}
