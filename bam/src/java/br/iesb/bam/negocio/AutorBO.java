package br.iesb.bam.negocio;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.dados.dao.AutorDAO;
import br.iesb.bam.dados.entidade.Autor;
import br.iesb.bam.exception.AlertaException;
import java.util.List;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.exception.GenericaException;
import static br.iesb.bam.util.Constantes.MSGA6;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Jacob Vilar
 */
public class AutorBO {

    private static AutorBO instance;

    private AutorBO() {
    }

    public static AutorBO getInstance() {
        if (instance == null) {
            instance = new AutorBO();
        }
        return instance;
    }

    public void incluir(Autor autor) throws ErroException, AlertaException {
        Autor existente = null;
        try {
            existente = AutorDAO.getInstance().consultarAutorExistente(autor);
            if (existente == null) {
                AutorDAO.getInstance().incluir(autor);
            } else {
                throw new AlertaException(Constantes.MSGA9);
            }
        } catch (HibernateException e) {
            throw new ErroException(Constantes.MSGE1,e);
        }
    }

    public void alterar(Autor autor) throws Exception {
        AutorDAO.getInstance().alterar(autor);
    }

    public void excluir(Autor autor) throws ErroException, AlertaException {
        try {
            AutorDAO.getInstance().excluir(autor);
        } catch (Exception ex) {
            if (ex instanceof ConstraintViolationException) {
                throw new AlertaException(MSGA6);
            }
            throw new ErroException(ex);
        }
    }

    public Autor consultarPorId(Integer id) throws Exception {
        return AutorDAO.getInstance().consultar(id);
    }

    public List<Autor> consultarPorNome(Autor autor) throws GenericaException, Exception {
        List<Autor> autores = AutorDAO.getInstance().consultarPorNome(autor);
        if (autores == null || autores.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return autores;
    }

    public List<Autor> listar() throws Exception {
        return AutorDAO.getInstance().listar();
    }

    public List<Autor> consultarPorExemplo(Autor autor) throws Exception {
        return AutorDAO.getInstance().consultarPorExemplo(autor);
    }
}
