package br.iesb.bam.dados.dao;

import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.EmprestimoObra;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EmprestimoObraDAO implements DAO<EmprestimoObra> {

    private static EmprestimoObraDAO instance;
    private Criteria criteria;

    private EmprestimoObraDAO() {

    }

    public static EmprestimoObraDAO getInstance() {
        if (instance == null) {
            instance = new EmprestimoObraDAO();
        }
        return instance;
    }

    @Override
    public EmprestimoObra incluir(EmprestimoObra emprestimoObra) throws HibernateException {
        getSessao().persist(emprestimoObra);
        getSessao().flush();
        return emprestimoObra;
    }

    @Override
    public EmprestimoObra alterar(EmprestimoObra emprestimoObra) throws HibernateException {
        getSessao().update(emprestimoObra);
        getSessao().flush();
        return emprestimoObra;
    }

    @Override
    public void excluir(EmprestimoObra emprestimoObra) throws HibernateException {
        getSessao().delete(emprestimoObra);
        getSessao().flush();
    }

    @Override
    public EmprestimoObra consultar(Integer id) throws HibernateException {
        throw new UnsupportedOperationException("Implementação não se aplica ao contexto desta entidade");
    }

    @Override
    public List<EmprestimoObra> listar() throws HibernateException {
        getCriteria();
        return criteria.list();
    }

    @Override
    public List<EmprestimoObra> consultarPorExemplo(EmprestimoObra emprestimoObra) throws HibernateException {
        throw new UnsupportedOperationException("Implementação não se aplica ao contexto desta entidade");
    }

    @Override
    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }

    @Override
    public void getCriteria() {
        criteria = getSessao().createCriteria(EmprestimoObra.class);
    }
}
