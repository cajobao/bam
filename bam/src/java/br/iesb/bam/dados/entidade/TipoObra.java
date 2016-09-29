package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jacob Vilar
 */
@Entity
@Table(name = "TB_TIPO_OBRA", schema = "bam")
public class TipoObra implements Serializable {

    @Id
    @Column(name = "id_tipo_obra")
    @SequenceGenerator(name = "tb_tipo_obra_id_tipo_obra_seq", sequenceName = "bam.tb_tipo_obra_id_tipo_obra_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_tipo_obra_id_tipo_obra_seq")
    private Integer idTipoObra;

    @Column(name = "nm_tipo_obra")
    private String nmTipoObra;

    public TipoObra() {
    }

    public TipoObra(Integer idTipoObra) {
        this.idTipoObra = idTipoObra;
    }

    public Integer getIdTipoObra() {
        return idTipoObra;
    }

    public void setIdTipoObra(Integer idTipoObra) {
        this.idTipoObra = idTipoObra;
    }

    public String getNmTipoObra() {
        return nmTipoObra;
    }

    public void setNmTipoObra(String nmTipoObra) {
        this.nmTipoObra = nmTipoObra;
    }

}
