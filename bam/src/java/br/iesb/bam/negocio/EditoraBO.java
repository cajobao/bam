package br.iesb.bam.negocio;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.dados.dao.EditoraDAO;
import br.iesb.bam.dados.entidade.Editora;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import static br.iesb.bam.util.Constantes.MSGA6;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author jacob.santana
 */
public class EditoraBO {

    private static EditoraBO instance;

    private EditoraBO() {
    }

    public static EditoraBO getInstance() {
        if (instance == null) {
            instance = new EditoraBO();
        }
        return instance;
    }

    public void incluir(Editora editora) throws ErroException, AlertaException {
        Editora existente = null;
        try {
            existente = EditoraDAO.getInstance().consultarEditoraExistente(editora);
            if (existente == null) {
                EditoraDAO.getInstance().incluir(editora);
            } else {
                throw new AlertaException(Constantes.MSGA9);
            }
        } catch (HibernateException ex) {
            throw new ErroException(Constantes.MSGE1,ex);
        }
    }

    public List<Editora> listar() throws Exception {
        return EditoraDAO.getInstance().listar();
    }

    public List<Editora> consultarPorNome(Editora editora) throws AlertaException, ErroException {
        List<Editora> editoras = null;
        try {
            editoras = EditoraDAO.getInstance().consultarPorExemplo(editora);
        } catch (Exception ex) {
            throw new ErroException(ex);
        }
        if (editoras == null || editoras.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return editoras;
    }

    public void excluir(Editora editora) throws ErroException, AlertaException {
        try {
            EditoraDAO.getInstance().excluir(editora);
        } catch (Exception ex) {
            if (ex instanceof ConstraintViolationException) {
                throw new AlertaException(MSGA6);
            }
            throw new ErroException(ex);
        }
    }
}
