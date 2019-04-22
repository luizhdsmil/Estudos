package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import org.junit.*;

public class CalculadoraTeste {

    private Calculadora calculadora;

    @Before
    public void setUp(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        int a = 5;
        int b = 3;

        int resultado = calculadora.somar(a,b);

        Assert.assertEquals(8,resultado);
    }

    @Test
    public void deveSubtrairDoisValores(){
        int a = 8;
        int b = 5;

        int resultado = calculadora.subtracao(a,b);

        Assert.assertEquals(3,resultado);
    }

    @Test
    public void deveDividirDoisValores(){
        int a = 6;
        int b = 3;
        int resultado = calculadora.dividir(a,b);

        Assert.assertEquals(2,resultado);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void develancarExcessaoDividirPorZero() throws NaoPodeDividirPorZeroException {
        int a = 5;
        int b = 0;

        calculadora.dividirPorZero(a,b);


    }
}
