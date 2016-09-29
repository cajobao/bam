package br.iesb.bam.dados.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_emprestimo", schema = "bam")
public class Emprestimo implements Serializable {

    @Id
    @Column(name = "id_emprestimo")
    @SequenceGenerator(name = "tb_emprestimo_id_emprestimo_seq", sequenceName = "bam.tb_emprestimo_id_emprestimo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_emprestimo_id_emprestimo_seq")
    private Integer idEmprestimo;

    @JoinColumn(name = "id_pessoa")
    @ManyToOne
    private Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_obra_emprestimo", schema = "bam", joinColumns = {
        @JoinColumn(name = "id_emprestimo", referencedColumnName = "id_emprestimo")},
            inverseJoinColumns = @JoinColumn(name = "id_obra"))
    private List<Obra> obras;

    @Column(name = "dt_emprestimo")
    @Temporal(TemporalType.DATE)
    private Date dtEmprestimo;

    @Column(name = "dt_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dtDevolucao;

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(Date dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public Date getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(Date dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
