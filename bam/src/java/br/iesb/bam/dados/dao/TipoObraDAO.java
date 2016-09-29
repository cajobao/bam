package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.TipoObra;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class TipoObraDAO implements DAO<TipoObra> {

    public static final String ID_TIPO_OBRA = "idTipoObra";
    public static final String NM_TIPO_OBRA = "nmTipoObra";
    private Criteria criteria;
    private static TipoObraDAO instancia;

    public static TipoObraDAO getInstance() {
        if (instancia == null) {
            instancia = new TipoObraDAO();
        }
        return instancia;
    }

    private TipoObraDAO() {
    }

    @Override
    public TipoObra incluir(TipoObra tipoObra) throws HibernateException {
        getSessao().persist(tipoObra);
        getSessao().flush();
        return tipoObra;
    }

    @Override
    public TipoObra alterar(TipoObra tipoObra) throws HibernateException {
        getSessao().update(tipoObra);
        getSessao().flush();
        return tipoObra;
    }

    @Override
    public void excluir(TipoObra tipoObra) throws HibernateException {
        getSessao().delete(tipoObra);
        getSessao().flush();
    }

    @Override
    public TipoObra consultar(Integer id) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_TIPO_OBRA, id));
        return (TipoObra) criteria.uniqueResult();
    }

    public List<TipoObra> consultarPorNome(String nome) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.ilike(NM_TIPO_OBRA, nome.toLowerCase(), MatchMode.START));
        return criteria.list();
    }

    @Override
    public List<TipoObra> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<TipoObra> consultarPorExemplo(TipoObra tipoObra) throws HibernateException {
        getCriteria();
        if (tipoObra.getIdTipoObra() != null) {
            criteria.add(Restrictions.eq(ID_TIPO_OBRA, tipoObra.getIdTipoObra()));
        }
        if (!StringUtils.isBlank(tipoObra.getNmTipoObra())) {
            criteria.add(Restrictions.eq(NM_TIPO_OBRA, tipoObra.getNmTipoObra()));
        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(TipoObra.class);
    }
}
