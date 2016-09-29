package br.iesb.bam.beans;

import br.iesb.bam.session.SessionContext;
import br.iesb.bam.util.Constantes;
import br.iesb.bam.dados.entidade.Usuario;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.GenericaException;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import br.iesb.bam.negocio.LoginBO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jacob.santana
 */
@ViewScoped
@ManagedBean
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
            validarCamposObrigatoriosConsulta();
            Usuario usuarioBD = LoginBO.getInstance().isUsuarioValido(usuario);
            if (usuarioBD != null) {
                SessionContext.getInstance().setAttribute(USUARIO_LOGADO, usuarioBD);
                redireciona(TELA_PRINCIPAL);
            } else {
                FacesContext.getCurrentInstance().validationFailed();
                adicionaMensagemAlerta(new AlertaException(MSGA10));
            }
        } catch (AlertaException a) {
            adicionaMensagemAlerta(a);
        } catch (GenericaException g) {
            adicionaMensagemErro(new ErroException(g.getMessage()));
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e.getMessage()));
        }
    }

    @Override
    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
        if (isBlank(usuario.getNmUsuario()) || isBlank(usuario.getDsSenha())) {
            throw new AlertaException(Constantes.MSGA1);
        }
    }
    
    public void sair() {
        try {
            SessionContext.getInstance().encerrarSessao();
            redireciona(TELA_PRINCIPAL);
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