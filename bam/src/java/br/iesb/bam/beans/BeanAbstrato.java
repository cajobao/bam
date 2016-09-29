package br.iesb.bam.beans;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.InfoException;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author jacob.santana
 */
public abstract class BeanAbstrato extends Constantes {

    private static final String ALERTA = "Alerta";
    private static final String ERRO = "Erro";
    private static final String SUCESSO = "Sucesso";
    protected static final String USUARIO_LOGADO = "usuarioLogado";
    protected static final String TELA_PRINCIPAL = "/bam/publico/consultarObras.xhtml";
    protected static final String TELA_LOGIN = "/bam/publico/login.xhtml";
    protected static final String TELA_CONSULTA_AUTOR = "/bam/restrito/manterAutor/consulta.xhtml";
    protected static final String TELA_INCLUSAO_AUTOR = "/bam/restrito/manterAutor/novo.xhtml";
    protected static final String TELA_CONSULTA_EMPRESTIMO = "/bam/restrito/emprestimo/consulta.xhtml";
    protected static final String TELA_INCLUSAO_EMPRESTIMO = "/bam/restrito/emprestimo/novo.xhtml";
    protected static final String TELA_CONSULTA_EDITORA = "/bam/restrito/manterEditora/consulta.xhtml";
    protected static final String TELA_INCLUSAO_EDITORA = "/bam/restrito/manterEditora/novo.xhtml";
    protected static final String TELA_INCLUSAO_OBRA = "/bam/restrito/manterObra/novo.xhtml";

    protected void adicionaMensagemInfo(InfoException info) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, SUCESSO, info.getMessage()));
    }

    protected void adicionaMensagemInfo(String info) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, SUCESSO, info));
    }

    protected void adicionaMensagemAlerta(AlertaException alerta) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ALERTA, alerta.getMessage()));
    }

    protected void adicionaMensagemAlerta(String alerta) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ALERTA, alerta));
    }

    protected void adicionaMensagemErro(ErroException erro) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRO, erro.getMessage()));
    }

    protected void adicionaMensagemErro(String erro) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERRO, erro));
    }

    public void redireciona(String url) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        extContext.redirect(url);
    }

    public String redirecionaOutcome(String outcome) {
        return outcome;
    }

    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
    }

    protected void validarCamposObrigatoriosInclusao() throws AlertaException {
    }

    protected void validarCamposObrigatoriosExclusao() throws AlertaException {
    }

    protected boolean isBlank(String string) {
        return StringUtils.isBlank(string);
    }

    public void abrirFormInclusao() {
    }

    public void abrirFormConsulta() {
    }

    public void excluir() {
    }

    public void cleanSubmittedValues(UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        if (component.getChildCount() > 0) {
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
    }

    public void limpar() {
    }

    public void consultar() {
    }

    public void incluir() {

    }
}
