package br.iesb.bam.dados.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_periodico", schema = "bam")
@PrimaryKeyJoinColumn(name = "id_obra", referencedColumnName = "id_obra")
public class Periodico extends Obra {

    @Column(name = "dt_publicacao")
    private Date dtPublicacao;

    public Date getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Date dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }
}
