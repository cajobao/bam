package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jacob.santana
 */
@Entity
@Table(name = "tb_usuario", schema = "public")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id_usuario", nullable = false)
    @SequenceGenerator(name = "tb_usuario_seq", sequenceName = "bam.tb_usuario_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_seq")
    private Integer idUsuario;

    @Column(name = "nm_usuario")
    private String nmUsuario;

    @Column(name = "ds_senha")
    private String dsSenha;

    @JoinColumn(name = "id_pessoa")
    @OneToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    public Usuario(Integer idUsuario, String nmUsuario, String dsSenha, Pessoa pessoa) {
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.dsSenha = dsSenha;
        this.pessoa = pessoa;
    }

    public Usuario() {
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }
}
