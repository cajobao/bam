package br.iesb.bam.negocio;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.dados.dao.EmprestimoDAO;
import br.iesb.bam.dados.dao.EmprestimoObraDAO;
import br.iesb.bam.dados.dao.PessoaDAO;
import br.iesb.bam.dados.dao.TipoPessoaDAO;
import br.iesb.bam.dados.entidade.Emprestimo;
import br.iesb.bam.dados.entidade.EmprestimoObra;
import br.iesb.bam.dados.entidade.EmprestimoObraPK;
import br.iesb.bam.dados.entidade.Obra;
import br.iesb.bam.dados.entidade.Pessoa;
import br.iesb.bam.dados.entidade.TipoPessoa;
import br.iesb.bam.exception.AlertaException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author jacob.santana
 */
public class EmprestimoBO {

    private static EmprestimoBO instance;

    private EmprestimoBO() {

    }

    public static EmprestimoBO getInstance() {
        if (instance == null) {
            instance = new EmprestimoBO();
        }
        return instance;
    }

    public List<TipoPessoa> listarTipoPessoas() throws Exception {
        return TipoPessoaDAO.getInstance().listarTipificado();
    }

    public List<Pessoa> consultarPessoaPorExemplo(Pessoa pessoa) throws Exception {
        List<Pessoa> pessoas = PessoaDAO.getInstance().consultarPorExemplo(pessoa);
        if (pessoas == null || pessoas.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return pessoas;
    }

    public void incluirEmprestimo(Emprestimo emprestimo, List<Obra> obras) throws AlertaException {
        try {
            emprestimo.setDtEmprestimo(new Date());
            emprestimo.setDtDevolucao(dataDevolucao());
            Emprestimo incluido = EmprestimoDAO.getInstance().incluir(emprestimo);
            for (Obra obra : obras) {
                EmprestimoObra eo = new EmprestimoObra(new EmprestimoObraPK(incluido.getIdEmprestimo(), obra.getIdObra()));
                EmprestimoObraDAO.getInstance().incluir(eo);
            }
        } catch (Exception e) {
            throw new AlertaException(e);
        }
    }

    private static Date dataDevolucao() {
        Calendar d = Calendar.getInstance();
        d.setTime(new Date());
        d.add(Calendar.DATE, 7);
        return DateUtils.truncate(d.getTime(), Calendar.DATE);
    }

    public List<Emprestimo> consultarEmprestimoPorObra(Obra obra) throws Exception {
        List<Emprestimo> emprestimos = EmprestimoDAO.getInstance().consultarPorObra(obra);
        if (emprestimos == null || emprestimos.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return emprestimos;
    }

    public List<Emprestimo> consultarEmprestimoPorPessoa(Pessoa pessoa) throws Exception {
        List<Emprestimo> emprestimos = EmprestimoDAO.getInstance().consultarPorPessoa(pessoa);
        if (emprestimos == null || emprestimos.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return emprestimos;
    }
}
