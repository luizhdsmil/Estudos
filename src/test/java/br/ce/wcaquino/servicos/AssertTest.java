package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class AssertTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void test(){

        Assert.assertTrue(true);
        Assert.assertFalse(false);
        Assert.assertEquals(0.51,0.51, 0.1);

        Usuario usuario = new Usuario("luiz");
        Usuario usuario2 = new Usuario("luiz");

        Assert.assertEquals(usuario,usuario2);

        //Assert.assertSame(usuario,usuario2); // mesma instacia

        Usuario usuario1 = new Usuario();
       // error.checkThat(usuario1, CoreMatchers.nullValue());
    }
}
