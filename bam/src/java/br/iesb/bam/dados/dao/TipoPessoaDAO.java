package br.iesb.bam.dados.dao;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.TipoPessoa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jacob.santana
 */
public class TipoPessoaDAO extends Constantes implements DAO<TipoPessoa> {

    public static final String ID_TIPO_PESSOA = "idTipoPessoa";
    public static final String NM_TIPO_PESSOA = "nmTipoPessoa";
    public static final String CD_TIPO_PESSOA = "cdTipoPessoa";
    private static TipoPessoaDAO instance;
    private Criteria criteria;

    private TipoPessoaDAO() {

    }

    public static TipoPessoaDAO getInstance() {
        if (instance == null) {
            instance = new TipoPessoaDAO();
        }
        return instance;
    }

    @Override
    public TipoPessoa incluir(TipoPessoa tipoPessoa) throws HibernateException {
        getSessao().persist(tipoPessoa);
        getSessao().flush();
        return tipoPessoa;
    }

    @Override
    public TipoPessoa alterar(TipoPessoa tipoPessoa) throws HibernateException {
        getSessao().update(tipoPessoa);
        getSessao().flush();
        return tipoPessoa;
    }

    @Override
    public void excluir(TipoPessoa tipoPessoa) throws HibernateException {
        getSessao().delete(tipoPessoa);
        getSessao().flush();
    }

    @Override
    public TipoPessoa consultar(Integer id) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoPessoa> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    public List<TipoPessoa> listarTipificado() throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.ne(CD_TIPO_PESSOA, TIPO_FUNCIONARIO));
        return criteria.list();
    }

    @Override
    public List<TipoPessoa> consultarPorExemplo(TipoPessoa tipoPessoa) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(TipoPessoa.class);
    }
}
