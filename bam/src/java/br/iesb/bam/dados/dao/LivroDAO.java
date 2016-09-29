package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Livro;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jacob Vilar
 */
public class LivroDAO implements DAO<Livro> {

    public static final String DS_IBSN = "ds_ibsn";
    public static final String ST_REFERENCIA = "st_referencia";
    private Criteria criteria;
    private static LivroDAO instance;

    private LivroDAO() {
    }

    public static LivroDAO getInstance() {
        if (instance == null) {
            instance = new LivroDAO();
        }
        return instance;
    }

    @Override
    public Livro incluir(Livro livro) throws HibernateException {
        getSessao().persist(livro);
        getSessao().flush();
        return livro;
    }

    @Override
    public Livro alterar(Livro livro) throws HibernateException {
        getSessao().update(livro);
        getSessao().flush();
        return livro;
    }

    @Override
    public void excluir(Livro livro) throws HibernateException {
        getSessao().delete(livro);
        getSessao().flush();
    }

    @Override
    public Livro consultar(Integer id) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ObraDAO.ID_OBRA, id));
        return (Livro) criteria.uniqueResult();
    }

    @Override
    public List<Livro> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Livro> consultarPorExemplo(Livro livro) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ObraDAO.ID_OBRA, livro.getIdObra()));
        criteria.add(Restrictions.eq(DS_IBSN, livro.getDsIbsn()));
        criteria.add(Restrictions.eq(ST_REFERENCIA, livro.getStReferencia()));
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Livro.class);
    }
}
