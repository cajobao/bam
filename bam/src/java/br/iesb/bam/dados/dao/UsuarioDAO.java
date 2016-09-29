package br.iesb.bam.dados.dao;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.dados.entidade.Usuario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jacob.santana
 */
public class UsuarioDAO {

    Criteria criteria;

    private static UsuarioDAO instancia;

    public static UsuarioDAO getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    public UsuarioDAO() {
        criteria = getSessao().createCriteria(Usuario.class);
    }

    public Usuario incluir(Usuario usuario) throws HibernateException {
        getSessao().persist(usuario);
        getSessao().flush();
        return usuario;
    }

    public Usuario consultaUsuarioPorNome(Usuario usuario) throws Exception {
        criteria.add(Restrictions.eq(Constantes.NM_USUARIO, usuario.getNmUsuario()));
        criteria.add(Restrictions.eq(Constantes.DS_SENHA, usuario.getDsSenha()));
        return (Usuario) criteria.uniqueResult();
    }

    public Session getSessao() {
        return HibernateUtil.getInstance().getSessao();
    }
}
