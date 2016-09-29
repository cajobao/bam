package br.iesb.bam.dados.dao;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Periodico;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jacob Vilar
 */
public class PeriodicoDAO implements DAO<Periodico> {

    private static final String DT_PUBLICACAO = "dtPublicacao";
    private static PeriodicoDAO instance;
    private Criteria criteria;

    private PeriodicoDAO() {
    }

    public static PeriodicoDAO getInstance() {
        if (instance == null) {
            instance = new PeriodicoDAO();
        }
        return instance;
    }

    @Override
    public Periodico incluir(Periodico periodico) throws HibernateException {
        getSessao().persist(periodico);
        getSessao().flush();
        return periodico;
    }

    @Override
    public Periodico alterar(Periodico periodico) throws HibernateException {
        getSessao().update(periodico);
        getSessao().flush();
        return periodico;
    }

    @Override
    public void excluir(Periodico periodico) throws HibernateException {
        getSessao().delete(periodico);
        getSessao().flush();
    }

    @Override
    public Periodico consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(ObraDAO.ID_OBRA, id));
        }
        return (Periodico) criteria.uniqueResult();
    }

    @Override
    public List<Periodico> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Periodico> consultarPorExemplo(Periodico periodico) throws HibernateException {
        getCriteria();
        if (periodico.getDtPublicacao() != null) {
            criteria.add(Restrictions.eq(DT_PUBLICACAO, periodico.getDtPublicacao()));
        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Periodico.class);
    }
}
