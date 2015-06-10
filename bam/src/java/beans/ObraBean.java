package beans;

import dados.dao.EditoraDAO;
import dados.dao.TipoObraDAO;
import dados.entidade.Autor;
import dados.entidade.Editora;
import dados.entidade.Obra;
import dados.entidade.TipoObra;
import exception.AlertaException;
import exception.ErroException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import negocio.AutorBO;
import negocio.ObraBO;

/**
 *
 * @author jacob.santana
 */
@SessionScoped
@Named(value = "obraBean")
public class ObraBean extends BeanAbstrato implements Serializable {

    private Obra obra;
    private Obra obraCadastro;
    private List<Obra> obras;
    private List<Editora> editoras;
    private List<Autor> autores;
    private List<TipoObra> tiposDeObra;

    public ObraBean() {
        try {
            this.editoras = EditoraDAO.getInstance().listar();
            this.autores = AutorBO.getInstance().listar();
            this.tiposDeObra = TipoObraDAO.getInstance().listar();
        } catch (Exception ex) {
            adicionaMensagemErro(new ErroException(ex.getMessage()));
        }
    }

    public void abrirFormConsulta() {
        try {
            redireciona(TELA_PRINCIPAL);
        } catch (IOException ex) {
            Logger.getLogger(ObraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirFormInclusao() {
        try {
            redireciona(TELA_INCLUSAO_OBRA);
        } catch (IOException ex) {
            Logger.getLogger(ObraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultar() {
        try {
            obras = ObraBO.getInstance().consultarObraPorExemplo(obra);
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        } catch (Exception e) {
            adicionaMensagemErro(new ErroException(e.getMessage()));
        }
        abrirFormConsulta();
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

    public Obra getObraCadastro() {
        return obraCadastro;
    }

    public void setObraCadastro(Obra obraCadastro) {
        this.obraCadastro = obraCadastro;
    }
}