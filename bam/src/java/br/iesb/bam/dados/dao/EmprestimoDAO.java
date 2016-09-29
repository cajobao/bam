package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Emprestimo;
import br.iesb.bam.dados.entidade.Obra;
import br.iesb.bam.dados.entidade.Pessoa;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class EmprestimoDAO implements DAO<Emprestimo> {

    private static final String ID_EMPRESTIMO = "idEmprestimo";
    private static final String DT_EMPRESTIMO = "dtEmprestimo";
    private static final String DT_DEVOLUCAO = "dtDevolucao";
    private static EmprestimoDAO instance;
    private Criteria criteria;

    private EmprestimoDAO() {

    }

    public static EmprestimoDAO getInstance() {
        if (instance == null) {
            instance = new EmprestimoDAO();
        }
        return instance;
    }

    @Override
    public Emprestimo incluir(Emprestimo emprestimo) throws HibernateException {
        getSessao().persist(emprestimo);
        getSessao().flush();
        return emprestimo;
    }

    @Override
    public Emprestimo alterar(Emprestimo emprestimo) throws HibernateException {
        getSessao().update(emprestimo);
        getSessao().flush();
        return emprestimo;
    }

    @Override
    public void excluir(Emprestimo emprestimo) throws HibernateException {
        getSessao().delete(emprestimo);
        getSessao().flush();
    }

    @Override
    public Emprestimo consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(ID_EMPRESTIMO, id));
        }
        return (Emprestimo) criteria.uniqueResult();
    }

    @Override
    public List<Emprestimo> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Emprestimo> consultarPorExemplo(Emprestimo emprestimo) throws HibernateException {
        getCriteria();
        if (emprestimo.getDtEmprestimo() != null) {
            criteria.add(Restrictions.eq(DT_EMPRESTIMO, emprestimo.getDtEmprestimo()));
        }
        if (emprestimo.getDtDevolucao() != null) {
            criteria.add(Restrictions.eq(DT_DEVOLUCAO, emprestimo.getDtDevolucao()));
        }
        if (emprestimo.getPessoa() != null) {
            criteria.createAlias("pessoa", "pessoaAlias");
            criteria.add(Restrictions.ilike("pessoaAlias.nmPessoa", emprestimo.getPessoa().getNmPessoa().toLowerCase(), MatchMode.START));
        }
        return criteria.list();
    }

    public List<Emprestimo> consultarPorObra(Obra obra) throws HibernateException {
        getCriteria();
        criteria.createAlias("obras", "obra");
        if (obra.getTipoObra().getIdTipoObra() != null) {
            criteria.add(Restrictions.eq("obra.tipoObra.idTipoObra", obra.getTipoObra().getIdTipoObra()));
        }
        if (!StringUtils.isBlank(obra.getDsTitulo())) {
            criteria.add(Restrictions.ilike("obra.dsTitulo", obra.getDsTitulo().toLowerCase(), MatchMode.START));
        }
        return criteria.list();

    }

    public List<Emprestimo> consultarPorPessoa(Pessoa pessoa) throws HibernateException {
        getCriteria();
        criteria.createAlias("pessoa", "pessoaAlias");
        if (!StringUtils.isBlank(pessoa.getNmPessoa())) {
            criteria.add(Restrictions.ilike("pessoaAlias.nmPessoa", pessoa.getNmPessoa().toLowerCase(), MatchMode.START));
        }
        if (pessoa.getNrMatricula() != null) {
            criteria.add(Restrictions.eq("pessoaAlias.nrMatricula", pessoa.getNrMatricula()));
        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Emprestimo.class);
    }
}
