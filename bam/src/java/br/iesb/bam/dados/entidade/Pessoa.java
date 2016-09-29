package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

    @Id
    @Column(name = "id_pessoa")
    @SequenceGenerator(name = "tb_pessoa_seq", sequenceName = "bam.tb_pessoa_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_pessoa_seq")
    private Integer idPessoa;

    @Column(name = "nm_pessoa")
    private String nmPessoa;

    @Column(name = "ed_email")
    private String edEmail;

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getEdEmail() {
        return edEmail;
    }

    public void setEdEmail(String edEmail) {
        this.edEmail = edEmail;
    }

    public Pessoa(Integer idPessoa, String nmPessoa, String edEmail) {
        this.idPessoa = idPessoa;
        this.nmPessoa = nmPessoa;
        this.edEmail = edEmail;
    }

    public Pessoa() {
    }
}
