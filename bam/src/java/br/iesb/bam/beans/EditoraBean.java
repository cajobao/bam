package br.iesb.bam.beans;

import br.iesb.bam.dados.entidade.Editora;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.InfoException;
import br.iesb.bam.negocio.EditoraBO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jacob.santana
 */
@ViewScoped
@ManagedBean
public class EditoraBean extends BeanAbstrato implements Serializable {

    private Editora editoraConsulta;
    private Editora editoraInclusao;
    private Editora editoraExclusao;
    private List<Editora> editoras;

    @PostConstruct
    public void init() {
        editoraConsulta = new Editora();
        editoraInclusao = new Editora();
        editoraExclusao = new Editora();
    }

    @Override
    public void consultar() {
        try {
            validarCamposObrigatoriosConsulta();
            editoras = EditoraBO.getInstance().consultarPorNome(editoraConsulta);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (ErroException erro) {
            adicionaMensagemErro(erro);
        }
    }

    @Override
    public void excluir() {
        try {
            EditoraBO.getInstance().excluir(editoraExclusao);
            adicionaMensagemInfo(new InfoException(MSGS3));
            consultar();
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (ErroException erro) {
            adicionaMensagemErro(erro);
        }
    }

    @Override
    public void incluir() {
        try {
            validarCamposObrigatoriosInclusao();
            EditoraBO.getInstance().incluir(editoraInclusao);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (ErroException erro) {
            adicionaMensagemErro(erro);
        }
    }

    @Override
    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
        if (isBlank(editoraConsulta.getNmEditora())) {
            throw new AlertaException(MSGA1);
        }
    }

    @Override
    protected void validarCamposObrigatoriosInclusao() throws AlertaException {
        if (isBlank(editoraInclusao.getNmEditora())) {
            throw new AlertaException(MSGA1);
        }
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }

    public Editora getEditoraConsulta() {
        return editoraConsulta;
    }

    public void setEditoraConsulta(Editora editoraConsulta) {
        this.editoraConsulta = editoraConsulta;
    }

    public Editora getEditoraInclusao() {
        return editoraInclusao;
    }

    public void setEditoraInclusao(Editora editoraInclusao) {
        this.editoraInclusao = editoraInclusao;
    }

    public Editora getEditoraExclusao() {
        return editoraExclusao;
    }

    public void setEditoraExclusao(Editora editoraExclusao) {
        this.editoraExclusao = editoraExclusao;
    }

}
