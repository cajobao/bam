package br.iesb.bam.beans;

import br.iesb.bam.dados.entidade.Autor;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.InfoException;
import br.iesb.bam.negocio.AutorBO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author jacob.santana
 */
@ManagedBean
@ViewScoped
public class AutorBean extends BeanAbstrato implements Serializable {

    private Autor autorConsulta;
    private Autor autorInclusao;
    private Autor autorExclusao;
    private List<Autor> autores;

    @PostConstruct
    public void init() {
        autorConsulta = new Autor();
        autorInclusao = new Autor();
        autorExclusao = new Autor();
    }

    public AutorBean() {
    }

    @Override
    public void consultar() {
        try {
            validarCamposObrigatoriosConsulta();
            autores = AutorBO.getInstance().consultarPorNome(autorConsulta);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception ex) {
            adicionaMensagemErro(new ErroException(ex));
        }
    }

    @Override
    public void incluir() {
        try {
            validarCamposObrigatoriosInclusao();
            AutorBO.getInstance().incluir(autorInclusao);
            adicionaMensagemInfo(new InfoException(MSGS1));
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (ErroException erro) {
            adicionaMensagemErro(erro);
        }
    }

    @Override
    public void excluir() {
        try {
            validarCamposObrigatoriosExclusao();
            AutorBO.getInstance().excluir(autorExclusao);
            adicionaMensagemInfo(new InfoException(MSGS1));
            consultar();
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (ErroException erro) {
            adicionaMensagemErro(erro);
        }
    }

    @Override
    public void limpar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
    }

    @Override
    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
        if (isBlank(autorConsulta.getNmAutor())) {
            throw new AlertaException(MSGA1);
        }
    }

    @Override
    protected void validarCamposObrigatoriosInclusao() throws AlertaException {
        if (isBlank(autorInclusao.getNmAutor())) {
            throw new AlertaException(MSGA1);
        }
    }

    @Override
    protected void validarCamposObrigatoriosExclusao() throws AlertaException {
        if (autorExclusao == null) {
            throw new AlertaException(MSGA5);
        }
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getAutorConsulta() {
        return autorConsulta;
    }

    public void setAutorConsulta(Autor autorConsulta) {
        this.autorConsulta = autorConsulta;
    }

    public Autor getAutorInclusao() {
        return autorInclusao;
    }

    public void setAutorInclusao(Autor autorInclusao) {
        this.autorInclusao = autorInclusao;
    }

    public Autor getAutorExclusao() {
        return autorExclusao;
    }

    public void setAutorExclusao(Autor autorExclusao) {
        this.autorExclusao = autorExclusao;
    }
}
