package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Enciclopedia;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jacob Vilar
 */
public class EnciclopediaDAO implements DAO<Enciclopedia> {

    private static final String DS_ASSUNTO = "dsAssunto";
    private static final String QTD_VOLUMES = "qtdVolumes";
    private static EnciclopediaDAO instance;
    private Criteria criteria;

    private EnciclopediaDAO() {
    }

    public static EnciclopediaDAO getInstance() {
        if (instance == null) {
            instance = new EnciclopediaDAO();
        }
        return instance;
    }

    @Override
    public Enciclopedia incluir(Enciclopedia enciclopedia) throws HibernateException {
        getSessao().persist(enciclopedia);
        getSessao().flush();
        return enciclopedia;
    }

    @Override
    public Enciclopedia alterar(Enciclopedia enciclopedia) throws HibernateException {
        getSessao().update(enciclopedia);
        getSessao().flush();
        return enciclopedia;
    }

    @Override
    public void excluir(Enciclopedia enciclopedia) throws HibernateException {
        getSessao().delete(enciclopedia);
        getSessao().flush();
    }

    @Override
    public Enciclopedia consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(ObraDAO.ID_OBRA, id));
        }
        return (Enciclopedia) criteria.uniqueResult();
    }

    @Override
    public List<Enciclopedia> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Enciclopedia> consultarPorExemplo(Enciclopedia enciclopedia) throws HibernateException {
        getCriteria();
        if (!StringUtils.isBlank(enciclopedia.getDsAssunto())) {
            criteria.add(Restrictions.ilike(DS_ASSUNTO, enciclopedia.getDsAssunto().toLowerCase(), MatchMode.START));
        }
        if (enciclopedia.getQtdVolumes() != null) {
            criteria.add(Restrictions.eq(QTD_VOLUMES, enciclopedia.getQtdVolumes()));
        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Enciclopedia.class);
    }
}
