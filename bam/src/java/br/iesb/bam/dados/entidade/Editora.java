package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_editora", schema = "bam")
public class Editora implements Serializable {

    @Id
    @SequenceGenerator(name = "tb_editora_id_editora_seq", schema = "bam")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_editora_id_editora_seq")
    @Column(name = "id_editora")
    private Integer idEditora;

    @Column(name = "nm_editora")
    private String nmEditora;

    public Editora() {
    }

    public Editora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public Integer getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public String getNmEditora() {
        return nmEditora;
    }

    public void setNmEditora(String nmEditora) {
        this.nmEditora = nmEditora;
    }
}
