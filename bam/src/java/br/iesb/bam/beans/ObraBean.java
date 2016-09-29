package br.iesb.bam.beans;

import br.iesb.bam.dados.entidade.Autor;
import br.iesb.bam.dados.entidade.Editora;
import br.iesb.bam.dados.entidade.Enciclopedia;
import br.iesb.bam.dados.entidade.Livro;
import br.iesb.bam.dados.entidade.Obra;
import br.iesb.bam.dados.entidade.Periodico;
import br.iesb.bam.dados.entidade.TipoObra;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.InfoException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.iesb.bam.negocio.AutorBO;
import br.iesb.bam.negocio.EditoraBO;
import br.iesb.bam.negocio.ObraBO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;

/**
 *
 * @author jacob.santana
 */
@ViewScoped
@ManagedBean
public class ObraBean extends BeanAbstrato implements Serializable {

    //objetos
    private Obra obra;
    private TipoObra tipoObra;
    private Editora editora;
    private Autor autor;
    private Livro livro;
    private Enciclopedia enciclopedia;
    private Periodico periodico;
    private UIForm form;

    //listas
    private List<Obra> obras;
    private List<Editora> editoras;
    private List<Autor> autores;
    private List<TipoObra> tiposDeObra;

    public ObraBean() {
        try {
            this.editoras = EditoraBO.getInstance().listar();
            this.autores = AutorBO.getInstance().listar();
            this.tiposDeObra = ObraBO.getInstance().listarTipoObra();
        } catch (Exception ex) {
            adicionaMensagemErro(new ErroException(ex.getMessage()));
        }
    }

    @Override
    public void abrirFormConsulta() {
        try {
            redireciona(TELA_PRINCIPAL);
        } catch (IOException ex) {
            Logger.getLogger(ObraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void abrirFormInclusao() {
        try {
            redireciona(TELA_INCLUSAO_OBRA);
        } catch (IOException ex) {
            Logger.getLogger(ObraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void consultar() {
        try {
            validarCamposObrigatoriosConsulta();
            obra.setAutor(autor);
            obra.setEditora(editora);
            obra.setTipoObra(tipoObra);
            obras = ObraBO.getInstance().consultarObraPorExemplo(obra);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e.getMessage()));
        }
    }

    @Override
    public void limpar() {
        obra = new Obra();
        cleanSubmittedValues(form);
    }
    
    

    public void cadastrar() {
        try {
            validarCamposObrigatoriosInclusao();
            if (tipoObra.getIdTipoObra() == TIPO_OBRA_LIVRO) {
                cadastrarLivro();
            } else if (tipoObra.getIdTipoObra() == TIPO_OBRA_ENCICLOPEDIA) {
                cadastrarEnciclopedia();
            } else if (tipoObra.getIdTipoObra() == TIPO_OBRA_PERIODICO) {
                cadastrarPeriodico();
            }
            adicionaMensagemInfo(new InfoException(MSGS1));
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    private void cadastrarLivro() throws Exception {
        livro.setDsTitulo(obra.getDsTitulo());
        livro.setQtdExemplares(obra.getQtdExemplares());
        livro.setEditora(editora);
        livro.setAutor(autor);
        livro.setTipoObra(tipoObra);
        ObraBO.getInstance().incluirLivro(livro);
    }

    private void cadastrarPeriodico() throws Exception {
        periodico.setDsTitulo(obra.getDsTitulo());
        periodico.setQtdExemplares(obra.getQtdExemplares());
        periodico.setEditora(editora);
        periodico.setAutor(autor);
        periodico.setTipoObra(tipoObra);
        ObraBO.getInstance().incluirPeriodico(periodico);
    }

    private void cadastrarEnciclopedia() throws Exception {
        enciclopedia.setDsTitulo(obra.getDsTitulo());
        enciclopedia.setQtdExemplares(obra.getQtdExemplares());
        enciclopedia.setEditora(editora);
        enciclopedia.setAutor(autor);
        enciclopedia.setTipoObra(tipoObra);
        ObraBO.getInstance().incluirEnciclopedia(enciclopedia);
    }

    @Override
    protected void validarCamposObrigatoriosInclusao() throws AlertaException {
        if (tipoObra.getIdTipoObra() == null || editora.getIdEditora() == null || autor.getIdAutor() == null || isBlank(obra.getDsTitulo())) {
            throw new AlertaException(MSGA1);
        } else if (tipoObra.getIdTipoObra() != null && tipoObra.getIdTipoObra() == TIPO_OBRA_LIVRO) {
            validarCamposObrigatoriosLivro();
        } else if (tipoObra.getIdTipoObra() != null && tipoObra.getIdTipoObra() == TIPO_OBRA_ENCICLOPEDIA) {
            validarCamposObrigatoriosEnciclopedia();
        } else if (tipoObra.getIdTipoObra() != null && tipoObra.getIdTipoObra() == TIPO_OBRA_PERIODICO) {
            validarCamposObrigatoriosPeriodico();
        }
    }

    @Override
    protected void validarCamposObrigatoriosConsulta() throws AlertaException {
        if (tipoObra.getIdTipoObra() == null && editora.getIdEditora() == null && autor.getIdAutor() == null && isBlank(obra.getDsTitulo())) {
            throw new AlertaException(MSGA1);
        }
    }

    private void validarCamposObrigatoriosLivro() throws AlertaException {
        if (isBlank(livro.getDsIbsn()) && isBlank(livro.getStReferencia())) {
            throw new AlertaException(MSGA1);
        }
    }

    private void validarCamposObrigatoriosEnciclopedia() throws AlertaException {
        if (isBlank(enciclopedia.getDsAssunto()) && enciclopedia.getQtdVolumes() == 0) {
            throw new AlertaException(MSGA1);
        }
    }

    private void validarCamposObrigatoriosPeriodico() throws AlertaException {
        if (periodico.getDtPublicacao() == null) {
            throw new AlertaException(MSGA1);
        }
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Obra getObra() {
        if (obra == null) {
            obra = new Obra();
        }
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<TipoObra> getTiposDeObra() {
        return tiposDeObra;
    }

    public void setTiposDeObra(List<TipoObra> tiposDeObra) {
        this.tiposDeObra = tiposDeObra;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public TipoObra getTipoObra() {
        if (tipoObra == null) {
            tipoObra = new TipoObra();
        }
        return tipoObra;
    }

    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }

    public Editora getEditora() {
        if (editora == null) {
            editora = new Editora();
        }
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        if (autor == null) {
            autor = new Autor();
        }
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Livro getLivro() {
        if (livro == null) {
            livro = new Livro();
        }
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Enciclopedia getEnciclopedia() {
        if (enciclopedia == null) {
            enciclopedia = new Enciclopedia();
        }
        return enciclopedia;
    }

    public void setEnciclopedia(Enciclopedia enciclopedia) {
        this.enciclopedia = enciclopedia;
    }

    public Periodico getPeriodico() {
        if (periodico == null) {
            periodico = new Periodico();
        }
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }
    
    
}
