package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Professor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ProfessorDAO implements DAO<Professor> {

    private Criteria criteria;

    @Override
    public Professor incluir(Professor professor) throws HibernateException {
        getSessao().persist(professor);
        getSessao().flush();
        return professor;
    }

    @Override
    public Professor alterar(Professor professor) throws HibernateException {
        getSessao().update(professor);
        getSessao().flush();
        return professor;
    }

    @Override
    public void excluir(Professor professor) throws HibernateException {
        getSessao().delete(professor);
        getSessao().flush();
    }

    @Override
    public Professor consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(PessoaDAO.ID_PESSOA, id));
        }
        return (Professor) criteria.uniqueResult();
    }

    @Override
    public List<Professor> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Professor> consultarPorExemplo(Professor professor) throws HibernateException {

        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Professor.class);
    }
}
