package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_obra", schema = "bam")
@Inheritance(strategy = InheritanceType.JOINED)
public class Obra implements Serializable {

    @Id
    @SequenceGenerator(name = "tb_obra_id_obra_seq", sequenceName = "bam.tb_obra_id_obra_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_obra_id_obra_seq")
    @Column(name = "id_obra")
    private Integer idObra;

    @JoinColumn(name = "id_editora")
    @ManyToOne(fetch = FetchType.EAGER)
    private Editora editora;

    @JoinColumn(name = "id_autor")
    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;

    @Column(name = "ds_titulo")
    private String dsTitulo;

    @JoinColumn(name = "id_tipo_obra")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoObra tipoObra;

    @Column(name = "qtd_exemplares")
    private Integer qtdExemplares;

    @ManyToMany(mappedBy = "obras", fetch = FetchType.EAGER)
    private List<Emprestimo> emprestimos;

    public Obra(Integer idObra) {
        this.idObra = idObra;
    }

    public Obra() {
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getDsTitulo() {
        return dsTitulo;
    }

    public void setDsTitulo(String dsTitulo) {
        this.dsTitulo = dsTitulo;
    }

    public TipoObra getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }

    public Integer getQtdExemplares() {
        return qtdExemplares;
    }

    public void setQtdExemplares(Integer qtdExemplares) {
        this.qtdExemplares = qtdExemplares;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
