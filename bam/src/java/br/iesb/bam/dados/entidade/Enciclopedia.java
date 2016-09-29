package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_enciclopedia", schema = "bam")
@PrimaryKeyJoinColumn(name = "id_obra", referencedColumnName = "id_obra")
public class Enciclopedia extends Obra implements Serializable {

    @Column(name = "ds_assunto")
    private String dsAssunto;

    @Column(name = "qtd_volumes")
    private Integer qtdVolumes;

    public String getDsAssunto() {
        return dsAssunto;
    }

    public void setDsAssunto(String dsAssunto) {
        this.dsAssunto = dsAssunto;
    }

    public Integer getQtdVolumes() {
        return qtdVolumes;
    }

    public void setQtdVolumes(Integer qtdVolumes) {
        this.qtdVolumes = qtdVolumes;
    }

    public Enciclopedia() {
    }
}
