package br.iesb.bam.beans;

import br.iesb.bam.dados.entidade.Emprestimo;
import br.iesb.bam.dados.entidade.Obra;
import br.iesb.bam.dados.entidade.Pessoa;
import br.iesb.bam.dados.entidade.TipoObra;
import br.iesb.bam.dados.entidade.TipoPessoa;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.InfoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.iesb.bam.negocio.EmprestimoBO;
import br.iesb.bam.negocio.ObraBO;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jacob.santana
 */
@ViewScoped
@ManagedBean
public class EmprestimoBean extends BeanAbstrato implements Serializable {

    //objetos
    private Pessoa pessoaConsulta;
    private Pessoa pessoaEmprestimo;
    private Obra obraEmprestimo;
    private Obra obraConsulta;
    private Obra obraRemovida;
    private Emprestimo emprestimo;
    //listas
    private List<TipoPessoa> tiposPessoa;
    private List<TipoObra> tiposObra;
    private List<Pessoa> pessoas;
    private List<Obra> obras;
    private List<Emprestimo> emprestimos;
    //controles
    private int abaAtiva;

    public EmprestimoBean() {
        try {
            this.pessoaConsulta = new Pessoa();
            this.pessoaConsulta.setTipoPessoa(new TipoPessoa());
            this.pessoaEmprestimo = new Pessoa();
            this.emprestimo = new Emprestimo();
            this.tiposPessoa = EmprestimoBO.getInstance().listarTipoPessoas();
            this.tiposObra = ObraBO.getInstance().listarTipoObra();
            this.obraConsulta = new Obra();
            this.obraConsulta.setTipoObra(new TipoObra());
            this.obras = new ArrayList<>();
        } catch (Exception ex) {
            adicionaMensagemErro(new ErroException(ex));
        }
    }

    @Override
    public void abrirFormInclusao() {
        try {
            redireciona(TELA_INCLUSAO_EMPRESTIMO);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    public void confirmar() {
        try {
            validarCamposObrigatoriosInclusao();
            emprestimo.setPessoa(pessoaEmprestimo);
            EmprestimoBO.getInstance().incluirEmprestimo(emprestimo, obras);
            adicionaMensagemInfo(new InfoException(MSGS1));
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    @Override
    public void consultar() {
        try {
            validarCamposObrigatoriosConsulta();
            if (abaAtiva == 0) {//consulta por pessoa
                emprestimos = EmprestimoBO.getInstance().consultarEmprestimoPorPessoa(pessoaConsulta);
            } else {
                emprestimos = EmprestimoBO.getInstance().consultarEmprestimoPorObra(obraConsulta);
            }

        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    public void consultarPessoa() {
        try {
            pessoas = EmprestimoBO.getInstance().consultarPessoaPorExemplo(pessoaConsulta);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    public void adicionarObra() {
        if (obraEmprestimo != null && obras.size() <= 5) {
            if (!obras.contains(obraEmprestimo)) {
                obras.add(obraEmprestimo);
            } else {
                adicionaMensagemAlerta(MSGA8);
            }
        } else {
            adicionaMensagemAlerta(new AlertaException(MSGA7));
        }
    }

    public void removerObra() {
        if (obraRemovida != null) {
            obras.remove(obraRemovida);
        }
    }

    @Override
    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
        if (abaAtiva == 0) {
            if (pessoaConsulta.getNrMatricula() == null && isBlank(pessoaConsulta.getNmPessoa())) {
                throw new AlertaException(MSGA3);
            }
        } else {
            if (obraConsulta.getTipoObra().getIdTipoObra() == null && isBlank(obraConsulta.getDsTitulo())) {
                throw new AlertaException(MSGA3);
            }
        }
    }

    @Override
    protected void validarCamposObrigatoriosInclusao() throws AlertaException {
        if (pessoaEmprestimo == null) {
            throw new AlertaException(MSGA11);
        }
        if (obras.isEmpty()) {
            throw new AlertaException(MSGA12);
        }
    }

    public Pessoa getPessoaConsulta() {
        return pessoaConsulta;
    }

    public void setPessoaConsulta(Pessoa pessoaConsulta) {
        this.pessoaConsulta = pessoaConsulta;
    }

    public Pessoa getPessoaEmprestimo() {
        return pessoaEmprestimo;
    }

    public void setPessoaEmprestimo(Pessoa pessoaEmprestimo) {
        this.pessoaEmprestimo = pessoaEmprestimo;
    }

    public List<TipoPessoa> getTiposPessoa() {
        return tiposPessoa;
    }

    public void setTiposPessoa(List<TipoPessoa> tiposPessoa) {
        this.tiposPessoa = tiposPessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Obra getObraEmprestimo() {
        return obraEmprestimo;
    }

    public void setObraEmprestimo(Obra obraEmprestimo) {
        this.obraEmprestimo = obraEmprestimo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Obra getObraRemovida() {
        return obraRemovida;
    }

    public void setObraRemovida(Obra obraRemovida) {
        this.obraRemovida = obraRemovida;
    }

    public List<TipoObra> getTiposObra() {
        return tiposObra;
    }

    public void setTiposObra(List<TipoObra> tiposObra) {
        this.tiposObra = tiposObra;
    }

    public Obra getObraConsulta() {
        return obraConsulta;
    }

    public void setObraConsulta(Obra obraConsulta) {
        this.obraConsulta = obraConsulta;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}
