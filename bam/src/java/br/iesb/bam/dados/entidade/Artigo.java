package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

public class Artigo extends Obra implements Serializable {

    private Date dtPublicacao;

    public Date getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Date dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

}
