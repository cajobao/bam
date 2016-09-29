package br.iesb.bam.beans;

import br.iesb.bam.dados.entidade.Obra;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jacob.santana
 */
@SessionScoped
@ManagedBean
public class PopupBean implements Serializable {

    private List<Obra> obrasEmprestimo;

    public PopupBean() {
    }

    public void abrirPopup(String outcome) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog(outcome, options, null);
    }

    public List<Obra> getObrasEmprestimo() {
        return obrasEmprestimo;
    }

    public void setObrasEmprestimo(List<Obra> obrasEmprestimo) {
        this.obrasEmprestimo = obrasEmprestimo;
    }

}
