/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.iesb.bam.dados.entidade.Pessoa;
import br.iesb.bam.dados.entidade.Usuario;
import br.iesb.bam.exception.AlertaException;
import br.iesb.bam.exception.ErroException;
import br.iesb.bam.negocio.UsuarioBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob Vilar
 */
public class UsuarioTest {
    
    public UsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        Usuario usuario = new Usuario(null, "jacob.vilar", "12345678", new Pessoa(null, "Jacob Vilar Santana", "cajobao@gmail.com"));
        try {
            usuario = UsuarioBO.getInstance().inserirUsuario(usuario);
        } catch (AlertaException ex) {
            Logger.getLogger(UsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroException ex) {
            Logger.getLogger(UsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
