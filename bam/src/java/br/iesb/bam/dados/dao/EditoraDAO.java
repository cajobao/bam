package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Editora;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class EditoraDAO implements DAO<Editora> {

    private static final String ID_EDITORA = "idEditora";
    private static final String NM_EDITORA = "nmEditora";
    private Criteria criteria;
    private static EditoraDAO instance;

    public static EditoraDAO getInstance() {
        if (instance == null) {
            instance = new EditoraDAO();
        }
        return instance;
    }

    private EditoraDAO() {

    }

    @Override
    public Editora incluir(Editora editora) throws HibernateException {
        getSessao().persist(editora);
        getSessao().flush();
        return editora;
    }

    @Override
    public Editora alterar(Editora editora) throws HibernateException {
        getSessao().update(editora);
        getSessao().flush();
        return editora;
    }

    @Override
    public void excluir(Editora editora) throws HibernateException {
        getSessao().delete(editora);
        getSessao().flush();
    }

    @Override
    public Editora consultar(Integer id) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_EDITORA, id));
        return (Editora) criteria.uniqueResult();
    }

    @Override
    public List<Editora> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Editora> consultarPorExemplo(Editora editora) throws HibernateException {
        getCriteria();
        if (editora.getIdEditora() != null) {
            criteria.add(Restrictions.eq(ID_EDITORA, editora.getIdEditora()));
        }
        if (!StringUtils.isBlank(editora.getNmEditora())) {
            criteria.add(Restrictions.ilike(NM_EDITORA, editora.getNmEditora().toLowerCase(), MatchMode.START));
        }
        return criteria.list();
    }

    public Editora consultarEditoraExistente(Editora editora) throws HibernateException {
        getCriteria();
        if (!StringUtils.isBlank(editora.getNmEditora())) {
            criteria.add(Restrictions.ilike(NM_EDITORA, editora.getNmEditora().toLowerCase(), MatchMode.START));
        }
        return (Editora) criteria.uniqueResult();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Editora.class);
    }
}
