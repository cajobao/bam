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
@Table(name = "tb_autor", schema = "bam")
public class Autor implements Serializable {

    @Id
    @Column(name = "id_autor")
    @SequenceGenerator(name = "tb_autor_id_autor_seq", sequenceName = "bam.tb_autor_id_autor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_autor_id_autor_seq")
    private Integer idAutor;

    @Column(name = "nm_autor")
    private String nmAutor;

    public Autor() {
    }

    public Autor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNmAutor() {
        return nmAutor;
    }

    public void setNmAutor(String nmAutor) {
        this.nmAutor = nmAutor;
    }
}
