package br.iesb.bam.negocio;

import br.iesb.bam.dados.dao.UsuarioDAO;
import br.iesb.bam.dados.entidade.Pessoa;
import br.iesb.bam.dados.entidade.Usuario;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.util.Constantes;
import br.iesb.bam.util.HibernateUtil;
import br.iesb.bam.util.StringUtil;
import org.hibernate.HibernateException;

/**
 *
 * @author Jacob Vilar
 */
public class UsuarioBO {

    private static UsuarioBO instancia;

    /**
     *
     * @return
     */
    public static UsuarioBO getInstance() {
        if (instancia == null) {
            instancia = new UsuarioBO();
        }
        return instancia;
    }

    /**
     * Verifica a partir de um login e senha, se o usuário é válido
     *
     * @param usuario
     * @return
     * @throws ErroException
     */
    public Usuario isUsuarioValido(Usuario usuario) throws ErroException, AlertaException {
        validarCredenciais(usuario);
        Usuario usuarioDoBanco = null;
//        try {
//            String senha = HashUtil.getHashMD5(usuario.getDsSenha());
//            usuario.setDsSenha(senha);
//            usuarioDoBanco = UsuarioDAO.getInstance().consultaUsuarioPorCredenciais(usuario);
//        } catch (ErroException erro) {
//            logger.error("Erro ao tentar encriptar senha", erro);
//            throw erro;
//        } catch (HibernateException e) {
//            throw new ErroException(e.getMessage());
//        }
        return usuarioDoBanco;
    }

    /**
     *
     * @param usuario
     * @return
     * @throws AlertaException
     */
    public Usuario inserirUsuario(Usuario usuario) throws AlertaException, ErroException {
        validarCamposInclusao(usuario);
        try {
            Pessoa pessoa = PessoaBO.getInstance().incluirPessoa(usuario.getPessoa());
            usuario.setPessoa(pessoa);
            usuario = UsuarioDAO.getInstance().incluir(usuario);
        } catch (HibernateException e) {
            HibernateUtil.getInstance().getTransaction().rollback();
            throw new ErroException(e);
        }
        HibernateUtil.getInstance().getTransaction().commit();
        return usuario;
    }

    private void validarCredenciais(Usuario usuario) throws AlertaException {
        if (StringUtil.isBlank(usuario.getNmUsuario()) || StringUtil.isBlank(usuario.getDsSenha())) {
            throw new AlertaException(Constantes.MSGA10);
        }
    }

    /**
     *
     * @param usuario
     * @throws AlertaException
     */
    protected void validarCamposInclusao(Usuario usuario) throws AlertaException {
        if (usuario == null || usuario.getPessoa() == null || StringUtil.isBlank(usuario.getNmUsuario())) {
            throw new AlertaException(Constantes.MSGA1);
        }
    }
}
