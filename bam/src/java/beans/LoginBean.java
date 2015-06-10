package beans;

import Session.SessionContext;
import Util.Constantes;
import dados.entidade.Usuario;
import exception.AlertaException;
import exception.ErroException;
import exception.GenericaException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import negocio.LoginBO;

/**
 *
 * @author jacob.santana
 */
@SessionScoped
@Named(value = "loginBean")
public class LoginBean extends BeanAbstrato implements Serializable {

    private Usuario usuario = new Usuario();

    @ManagedProperty(value = "#{loginBO}")
    private LoginBO loginBO;

    public LoginBean() {

    }

    public Usuario getUsuarioLogado() {
        return (Usuario) SessionContext.getInstance().getUsuarioLogado();
    }

    public String abrirFormLogin() {
        return TELA_LOGIN;
    }

    public void autenticar() {
        try {
            validarCamposObrigatorios();
            Usuario usuarioBD = LoginBO.getInstance().isUsuarioValido(usuario);
            if (usuarioBD != null) {
                SessionContext.getInstance().setAttribute(USUARIO_LOGADO, usuarioBD);
                redireciona(TELA_PRINCIPAL);
            } else {
                adicionaMensagemAlerta(new AlertaException(Constantes.LOGIN_INVALIDO));
                FacesContext.getCurrentInstance().validationFailed();
                redireciona(TELA_LOGIN);
            }
        } catch (AlertaException a) {
            adicionaMensagemAlerta(a);
        } catch (GenericaException g) {
            adicionaMensagemErro(new ErroException(g.getMessage()));
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e.getMessage()));
        }
    }

    private void validarCamposObrigatorios() throws AlertaException {
        if (usuario.getNmUsuario().equals(STRING_BRANCO) || usuario.getDsSenha().equals(STRING_BRANCO)) {
            throw new AlertaException(Constantes.MSGA1);
        }
    }

    public void sair() {
        try {
            SessionContext.getInstance().encerrarSessao();
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext extContext = ctx.getExternalContext();
            extContext.redirect(TELA_PRINCIPAL);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e.getMessage()));
        }
    }

    public void limpar() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginBO getLoginBO() {
        return loginBO;
    }

    public void setLoginBO(LoginBO loginBO) {
        this.loginBO = loginBO;
    }
}