package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Autor;
import br.iesb.bam.dados.entidade.Editora;
import br.iesb.bam.dados.entidade.Obra;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;

public class ObraDAO implements DAO<Obra> {

    public static final String ID_OBRA = "idObra";
    private static final String ID_AUTOR = "autor.idAutor";
    private static final String ID_EDITORA = "editora.idEditora";
    private static final String ID_TIPO_OBRA = "tipoObra.idTipoObra";
    public static final String DS_TITULO = "dsTitulo";
    private Criteria criteria;
    private static ObraDAO instance;

    private ObraDAO() {
    }

    public static ObraDAO getInstance() {
        if (instance == null) {
            instance = new ObraDAO();
        }
        return instance;
    }

    @Override
    public Obra incluir(Obra obra) throws HibernateException {
        getSessao().persist(obra);
        getSessao().flush();
        return obra;
    }

    @Override
    public Obra alterar(Obra obra) throws HibernateException {
        getSessao().update(obra);
        getSessao().flush();
        return obra;
    }

    @Override
    public List<Obra> listar() throws HibernateException {
        getCriteria();
        return criteria.list();

    }

    @Override
    public void excluir(Obra obra) throws HibernateException {
        getSessao().delete(obra);
        getSessao().flush();
    }

    @Override
    public Obra consultar(Integer id) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_OBRA, id));
        return (Obra) criteria.uniqueResult();
    }

    @Override
    public List<Obra> consultarPorExemplo(Obra obra) throws HibernateException {
        getCriteria();
        if (obra.getAutor().getIdAutor() != null) {
            criteria.add(Restrictions.eq(ID_AUTOR, obra.getAutor().getIdAutor()));
        }
        if (obra.getEditora().getIdEditora() != null) {
            criteria.add(Restrictions.eq(ID_EDITORA, obra.getEditora().getIdEditora()));
        }
        if (obra.getTipoObra().getIdTipoObra() != null) {
            criteria.add(Restrictions.eq(ID_TIPO_OBRA, obra.getTipoObra().getIdTipoObra()));
        }
        if (!StringUtils.isBlank(obra.getDsTitulo())) {
            criteria.add(Restrictions.ilike(DS_TITULO, obra.getDsTitulo(), MatchMode.START));
        }
        return criteria.list();
    }

    public List<Obra> consultarObrasPorAutor(Autor autor) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_AUTOR, autor.getIdAutor()));
        return criteria.list();
    }

    public List<Obra> consultarObrasPorEditora(Editora editora) throws HibernateException {
        getCriteria();
        criteria.add(Restrictions.eq(ID_EDITORA, editora.getIdEditora()));
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Obra.class);
    }
}
