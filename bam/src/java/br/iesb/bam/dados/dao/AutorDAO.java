package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Autor;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class AutorDAO implements DAO<Autor> {

    private static final String ID_AUTOR = "idAutor";
    private static final String NM_AUTOR = "nmAutor";
    private Criteria criteria;
    private static AutorDAO instancia;

    public static AutorDAO getInstance() {
        if (instancia == null) {
            instancia = new AutorDAO();
        }
        return instancia;
    }

    private AutorDAO() {
    }

    @Override
    public Autor incluir(Autor autor) throws HibernateException {
        getSessao().persist(autor);
        getSessao().flush();
        return autor;
    }

    @Override
    public Autor alterar(Autor autor) throws HibernateException {
        getSessao().update(autor);
        getSessao().flush();
        return autor;
    }

    @Override
    public void excluir(Autor autor) throws HibernateException {
        getSessao().delete(autor);
        getSessao().flush();
    }

    @Override
    public Autor consultar(Integer id) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_AUTOR, id));
        return (Autor) criteria.uniqueResult();
    }

    public List<Autor> consultarPorNome(Autor autor) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.ilike(NM_AUTOR, autor.getNmAutor().toLowerCase(), MatchMode.START));
        return criteria.list();
    }

    @Override
    public List<Autor> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Autor> consultarPorExemplo(Autor autor) throws HibernateException {
        getCriteria();
        if (autor.getIdAutor() != null) {
            criteria.add(Restrictions.eq(ID_AUTOR, autor.getIdAutor()));
        }
        if (!StringUtils.isBlank(autor.getNmAutor())) {
            criteria.add(Restrictions.ilike(NM_AUTOR, autor.getNmAutor().toLowerCase(), MatchMode.START));
        }
        return criteria.list();
    }

    public Autor consultarAutorExistente(Autor autor) throws HibernateException {
        getCriteria();
        if (!StringUtils.isBlank(autor.getNmAutor())) {
            criteria.add(Restrictions.ilike(NM_AUTOR, autor.getNmAutor().toLowerCase(), MatchMode.START));
        }
        return (Autor) criteria.uniqueResult();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Autor.class);
    }
}
