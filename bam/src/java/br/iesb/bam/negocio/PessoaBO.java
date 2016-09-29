package br.iesb.bam.negocio;

import br.iesb.bam.dados.dao.PessoaDAO;
import br.iesb.bam.dados.entidade.Pessoa;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.util.Constantes;
import br.iesb.bam.util.StringUtil;

/**
 *
 * @author Jacob Vilar
 */
public class PessoaBO {

    private static PessoaBO instancia;

    public static PessoaBO getInstance() {
        if (instancia == null) {
            instancia = new PessoaBO();
        }
        return instancia;
    }

    public Pessoa consultarPessoaPorId(Integer id) {
        return PessoaDAO.getInstance().consultar(id);
    }

    public Pessoa incluirPessoa(Pessoa pessoa) throws AlertaException {
        validarCamposInclusao(pessoa);
        return PessoaDAO.getInstance().incluir(pessoa);
    }

    public void validarCamposInclusao(Pessoa pessoa) throws AlertaException {
        if (pessoa == null || StringUtil.isBlank(pessoa.getNmPessoa()) || StringUtil.isBlank(pessoa.getEdEmail())) {
            throw new AlertaException(Constantes.MSGA1);
        }
    }
}
