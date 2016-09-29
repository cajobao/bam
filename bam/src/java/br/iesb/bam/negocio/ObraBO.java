/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.bam.negocio;

import br.iesb.bam.util.Constantes;
import br.iesb.bam.dados.dao.EnciclopediaDAO;
import br.iesb.bam.dados.dao.LivroDAO;
import br.iesb.bam.dados.dao.ObraDAO;
import br.iesb.bam.dados.dao.PeriodicoDAO;
import br.iesb.bam.dados.dao.TipoObraDAO;
import br.iesb.bam.dados.entidade.Enciclopedia;
import br.iesb.bam.dados.entidade.Livro;
import br.iesb.bam.dados.entidade.Obra;
import br.iesb.bam.dados.entidade.Periodico;
import br.iesb.bam.dados.entidade.TipoObra;
import br.iesb.bam.exception.AlertaException;
import java.util.List;

/**
 *
 * @author jacob.santana
 */
public class ObraBO {

    private static ObraBO instance;

    private ObraBO() {

    }

    public static ObraBO getInstance() {
        if (instance == null) {
            instance = new ObraBO();
        }
        return instance;
    }

    public List<Obra> consultarObraPorExemplo(Obra obra) throws Exception {
        List<Obra> obras = ObraDAO.getInstance().consultarPorExemplo(obra);
        if (obras == null || obras.isEmpty()) {
            throw new AlertaException(Constantes.MSGA2);
        }
        return obras;
    }
    
    public void incluirLivro(Livro livro) throws Exception {
        LivroDAO.getInstance().incluir(livro);
    }
    
    public void incluirEnciclopedia(Enciclopedia enciclopedia) throws Exception {
        EnciclopediaDAO.getInstance().incluir(enciclopedia);
    }
    
    public void incluirPeriodico(Periodico periodico) throws Exception {
        PeriodicoDAO.getInstance().incluir(periodico);
    }
    
    public List<TipoObra> listarTipoObra() throws Exception {
        return TipoObraDAO.getInstance().listar();
    }
}
