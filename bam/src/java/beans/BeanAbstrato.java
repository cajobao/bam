package beans;

import Util.Constantes;
import exception.AlertaException;
import exception.ErroException;
import exception.InfoException;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import negocio.AutorBO;

/**
 *
 * @author jacob.santana
 */
public abstract class BeanAbstrato {

    protected static final String USUARIO_LOGADO = "usuarioLogado";
    protected static final String STRING_BRANCO = "";

    protected static final String TELA_PRINCIPAL = "/bam/publico/consultarObras.xhtml";
    protected static final String TELA_LOGIN = "/bam/publico/login.xhtml";

    protected static final String TELA_CONSULTA_AUTOR = "/bam/restrito/manterAutor/consulta.xhtml";
    protected static final String TELA_INCLUSAO_AUTOR = "/bam/restrito/manterAutor/novo.xhtml";

    protected static final String TELA_INCLUSAO_OBRA = "/bam/restrito/manterObra/novo.xhtml";

    protected void adicionaMensagemInfo(InfoException info) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.SUCESSO, info.getMessage()));
    }

    protected void adicionaMensagemAlerta(AlertaException alerta) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.ALERTA, alerta.getMessage()));
    }

    protected void adicionaMensagemErro(ErroException erro) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.ERRO, erro.getMessage()));
    }

    protected void redireciona(String url) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        extContext.redirect(url);
    }
}
