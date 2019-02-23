package br.ce.wcaquino.servicos;

import java.util.Date;

import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import sun.font.CoreMetrics;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
    public ExpectedException exception = ExpectedException.none();

	@Test
	public void teste() throws Exception {
		Usuario usuario = new Usuario();
		LocacaoService locacaoService = new LocacaoService();
		Filme filme = new Filme();

		
		usuario.setNome("Luiz");
		
		filme.setNome("Os Espiões");
		filme.setEstoque(5);
		filme.setPrecoLocacao(5.0);

		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));



	}

	@Test
	public void testeSemestoque(){

		Usuario usuario = new Usuario();
		LocacaoService locacaoService = new LocacaoService();
		Filme filme = new Filme();


		usuario.setNome("Luiz");

		filme.setNome("Os Espiões");
		filme.setEstoque(0);
		filme.setPrecoLocacao(5.0);

		try {
			Locacao locacao = locacaoService.alugarFilme(usuario, filme);
			error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));
		}catch (Exception e){
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque!"));
		}

	}

	@Test(expected = Exception.class)
	public void testeSemestoqu2() throws Exception{

		Usuario usuario = new Usuario();
		LocacaoService locacaoService = new LocacaoService();
		Filme filme = new Filme();


		usuario.setNome("Luiz");

		filme.setNome("Os Espiões");
		filme.setEstoque(0);
		filme.setPrecoLocacao(5.0);


			Locacao locacao = locacaoService.alugarFilme(usuario, filme);
			error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));
		}

    @Test()
    public void teste3() throws Exception {
        Usuario usuario = new Usuario();
        LocacaoService locacaoService = new LocacaoService();
        Filme filme = new Filme();


        usuario.setNome("Luiz");

        filme.setNome("Os Espiões");
        filme.setEstoque(0);
        filme.setPrecoLocacao(5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque!");

        Locacao locacao = locacaoService.alugarFilme(usuario, filme);
        error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));



    }

    @Test
    public void testLocadoraUsuaioFilmeVazio() throws FilmeSemEstoqueException{
	    LocacaoService locacaoService = new LocacaoService();

	    Filme filme = new Filme("Filme 2", 5, 5.0);


        try {
            locacaoService.alugarFilme(null, filme);
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(),CoreMatchers.is("Usuario vazio!"));
        }
    }

    @Test
    public void testLocadoraFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Luiz");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio!");

        locacaoService.alugarFilme(usuario, null);



    }

}
