package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jacob Vilar
 */
@Entity
@Table(name = "TB_TIPO_PESSOA", schema = "bam")
public class TipoPessoa implements Serializable {

    @Id
    @Column(name = "id_tipo_pessoa")
    @SequenceGenerator(name = "tb_tipo_pessoa_id_tipo_pessoa_seq", sequenceName = "bam.tb_tipo_pessoa_id_tipo_pessoa_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_tipo_pessoa_id_tipo_pessoa_seq")
    private Integer idTipoPessoa;

    @Column(name = "nm_tipo_pessoa")
    private String nmTipoPessoa;

    @Column(name = "cd_tipo_pessoa")
    private String cdTipoPessoa;

    public TipoPessoa() {
    }

    public TipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public Integer getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getNmTipoPessoa() {
        return nmTipoPessoa;
    }

    public void setNmTipoPessoa(String nmTipoPessoa) {
        this.nmTipoPessoa = nmTipoPessoa;
    }

    public String getCdTipoPessoa() {
        return cdTipoPessoa;
    }

    public void setCdTipoPessoa(String cdTipoPessoa) {
        this.cdTipoPessoa = cdTipoPessoa;
    }
}
