package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Pessoa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jacob.santana
 */
public class PessoaDAO implements DAO<Pessoa> {

    public static final String ID_PESSOA = "idPessoa";
    private static final String ID_TIPO_PESSOA = "tipoPessoa.idTipoPessoa";
    public static final String NM_PESSOA = "nmPessoa";
    private static final String NR_MATRICULA = "nrMatricula";
    private static PessoaDAO instance;
    private Criteria criteria;

    private PessoaDAO() {

    }

    public static PessoaDAO getInstance() {
        if (instance == null) {
            instance = new PessoaDAO();
        }
        return instance;
    }

    @Override
    public Pessoa incluir(Pessoa pessoa) throws HibernateException {
        getSessao().persist(pessoa);
        getSessao().flush();
        return pessoa;
    }

    @Override
    public Pessoa alterar(Pessoa pessoa) throws HibernateException {
        getSessao().update(pessoa);
        getSessao().flush();
        return pessoa;
    }

    @Override
    public void excluir(Pessoa pessoa) throws HibernateException {
        getSessao().delete(pessoa);
        getSessao().flush();
    }

    @Override
    public Pessoa consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(ID_PESSOA, id));
        }
        return (Pessoa) criteria.uniqueResult();
    }

    @Override
    public List<Pessoa> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Pessoa> consultarPorExemplo(Pessoa pessoa) throws HibernateException {
        getCriteria();
//        if (pessoa.getTipoPessoa().getIdTipoPessoa() != null) {
//            criteria.add(Restrictions.eq(ID_TIPO_PESSOA, pessoa.getTipoPessoa().getIdTipoPessoa()));
//        }
//        if (!StringUtils.isBlank(pessoa.getNmPessoa())) {
//            criteria.add(Restrictions.ilike(NM_PESSOA, pessoa.getNmPessoa().toLowerCase(), MatchMode.START));
//        }
//        if (pessoa.getNrMatricula() != null) {
//            criteria.add(Restrictions.eq(NR_MATRICULA, pessoa.getNrMatricula()));
//        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Pessoa.class);
    }
}
