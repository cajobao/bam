package beans;

import Util.Constantes;
import dados.entidade.Autor;
import exception.AlertaException;
import exception.ErroException;
import exception.InfoException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import negocio.AutorBO;

/**
 *
 * @author Jacob Vilar
 */
@SessionScoped
@Named(value = "autorBean")
public class AutorBean extends BeanAbstrato implements Serializable {

    private Autor autor = new Autor();
    private List<Autor> autores;

    public AutorBean() {

    }

    public void abrirFormInclusao() {
        limpar();
        try {
            redireciona(TELA_INCLUSAO_AUTOR);
        } catch (IOException ex) {
            Logger.getLogger(AutorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirFormConsulta() {
        limpar();
        try {
            redireciona(TELA_CONSULTA_AUTOR);
        } catch (IOException ex) {
            Logger.getLogger(AutorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            AutorBO.getInstance().incluir(autor);
            limpar();
            adicionaMensagemInfo(new InfoException(Constantes.MSGS1));
        } catch (ErroException e) {
            adicionaMensagemErro(e);
        }
    }

    public void consultar() {
        try {
            autores = AutorBO.getInstance().consultarPorNome(autor.getNmAutor());
        } catch (AlertaException alerta) {
            adicionaMensagemAlerta(alerta);
        }catch(Exception e) {
            adicionaMensagemErro(new ErroException(e));
        }
    }

    public void limpar() {
        autor = new Autor();
        autores = null;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getAutores() throws Exception {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}