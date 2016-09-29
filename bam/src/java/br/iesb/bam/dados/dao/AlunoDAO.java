package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Aluno;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class AlunoDAO implements DAO<Aluno> {

    private static final String DS_CURSO = "dsCurso";
    private static AlunoDAO instance;
    private Criteria criteria;

    private AlunoDAO() {
    }

    public static AlunoDAO getInstance() {
        if (instance == null) {
            instance = new AlunoDAO();
        }
        return instance;
    }

    @Override
    public Aluno incluir(Aluno aluno) throws HibernateException {
        getSessao().persist(aluno);
        getSessao().flush();
        return aluno;
    }

    @Override
    public Aluno alterar(Aluno aluno) throws HibernateException {
        getSessao().update(aluno);
        getSessao().flush();
        return aluno;
    }

    @Override
    public void excluir(Aluno aluno) throws HibernateException {
        getSessao().delete(aluno);
    }

    @Override
    public Aluno consultar(Integer id) throws HibernateException {
        getCriteria();
        if (id != null) {
            criteria.add(Restrictions.eq(PessoaDAO.ID_PESSOA, id));
        }
        return (Aluno) criteria.uniqueResult();
    }

    @Override
    public List<Aluno> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<Aluno> consultarPorExemplo(Aluno aluno) throws HibernateException {
        getCriteria();
        if (!StringUtils.isBlank(aluno.getNmPessoa())) {
            criteria.add(Restrictions.ilike(PessoaDAO.NM_PESSOA, aluno.getNmPessoa().toLowerCase(), MatchMode.START));
        }
        if (!StringUtils.isBlank(aluno.getDsCurso())) {
            criteria.add(Restrictions.ilike(DS_CURSO, aluno.getDsCurso().toLowerCase(), MatchMode.START));
        }
        return criteria.list();
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(Aluno.class);
    }
}
