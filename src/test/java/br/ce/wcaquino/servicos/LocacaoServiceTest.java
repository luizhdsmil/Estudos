package br.ce.wcaquino.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

public class LocacaoServiceTest {

	private LocacaoService locacaoService;

    private List<Filme> filmes;

    private Usuario usuario;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
    public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp(){
		locacaoService = new LocacaoService();
        usuario = new Usuario();
        filmes = new ArrayList<>();
        usuario.setNome("Luiz");
		//System.out.println("Antes de cada Método");
	}

	@After
	public void setUpAfter(){
		//System.out.println("Depois de cada Método");
	}

	@AfterClass
	public static void setUpAfterClass(){
		//System.out.println("Depois ds Classe");
	}

	@BeforeClass
	public static void setUpBeforeClass(){
		//System.out.println("Antes da Classe");
	}

	@Test
	public void teste() throws Exception {

		Filme filme = new Filme();
		filme.setNome("Os Espiões");
		filme.setEstoque(5);
		filme.setPrecoLocacao(5.0);
		Filme filme2 = new Filme();
		filme2.setNome("Os Espiões");
		filme2.setEstoque(5);
		filme2.setPrecoLocacao(5.0);
		Filme filme3 = new Filme();
		filme3.setNome("Os Espiões");
		filme3.setEstoque(5);
		filme3.setPrecoLocacao(5.0);

		filmes.add(filme);
		filmes.add(filme2);
		filmes.add(filme3);

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
		error.checkThat(locacao.getValor(), CoreMatchers.is(15.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));



	}

	@Test
	public void testeSemestoque(){

		Filme filme = new Filme();
		filme.setNome("Os Espiões");
		filme.setEstoque(5);
		filme.setPrecoLocacao(5.0);
		Filme filme2 = new Filme();
		filme2.setNome("Os Espiões");
		filme2.setEstoque(5);
		filme2.setPrecoLocacao(5.0);
		Filme filme3 = new Filme();
		filme3.setNome("Os Espiões");
		filme3.setEstoque(5);
		filme3.setPrecoLocacao(5.0);

		filmes.add(filme);
		filmes.add(filme2);
		filmes.add(filme3);



		try {
			Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
			error.checkThat(locacao.getValor(), CoreMatchers.is(15.0));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));
		}catch (Exception e){
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque!"));
		}

	}

	@Test(expected = Exception.class)
	public void testeSemestoqu2() throws Exception{

        Filme filme = new Filme();
        filme.setNome("Os Espiões");
        filme.setEstoque(5);
        filme.setPrecoLocacao(5.0);
        Filme filme2 = new Filme();
        filme2.setNome("Os Espiões");
        filme2.setEstoque(0);
        filme2.setPrecoLocacao(5.0);
        Filme filme3 = new Filme();
        filme3.setNome("Os Espiões");
        filme3.setEstoque(5);
        filme3.setPrecoLocacao(5.0);

        filmes.add(filme);
        filmes.add(filme2);
        filmes.add(filme3);


        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        error.checkThat(locacao.getValor(), CoreMatchers.is(15.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));
    }

    @Test()
    public void teste3() throws Exception {

		Filme filme = new Filme();
		filme.setNome("Os Espiões");
		filme.setEstoque(5);
		filme.setPrecoLocacao(5.0);
		Filme filme2 = new Filme();
		filme2.setNome("Os Espiões");
		filme2.setEstoque(5);
		filme2.setPrecoLocacao(5.0);
		Filme filme3 = new Filme();
		filme3.setNome("Os Espiões");
		filme3.setEstoque(0);
		filme3.setPrecoLocacao(5.0);

		filmes.add(filme);
		filmes.add(filme2);
		filmes.add(filme3);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque!");

        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        error.checkThat(locacao.getValor(), CoreMatchers.is(15.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1	)), CoreMatchers.is(true));



    }

    @Test
    public void testLocadoraUsuaioFilmeVazio() throws FilmeSemEstoqueException{

		Filme filme = new Filme();
		filme.setNome("Os Espiões");
		filme.setEstoque(5);
		filme.setPrecoLocacao(5.0);
		Filme filme2 = new Filme();
		filme2.setNome("Os Espiões");
		filme2.setEstoque(5);
		filme2.setPrecoLocacao(5.0);
		Filme filme3 = new Filme();
		filme3.setNome("Os Espiões");
		filme3.setEstoque(5);
		filme3.setPrecoLocacao(5.0);

		filmes.add(filme);
		filmes.add(filme2);
		filmes.add(filme3);

        try {
            locacaoService.alugarFilme(null, filmes);
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(),CoreMatchers.is("Usuario vazio!"));
        }
    }

    @Test
    public void testLocadoraFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio!");

        locacaoService.alugarFilme(usuario, null);

    }


    @Test
    public void teste_desconto_de_25_porcento_no_terceiro_filme() throws LocadoraException, FilmeSemEstoqueException {

        Filme filme = new Filme();
        filme.setNome("Os Espiões 1");
        filme.setEstoque(5);
        filme.setPrecoLocacao(10.0);
        Filme filme2 = new Filme();
        filme2.setNome("Os Espiões 2");
        filme2.setEstoque(5);
        filme2.setPrecoLocacao(10.0);
        Filme filme3 = new Filme();
        filme3.setNome("Os Espiões 3");
        filme3.setEstoque(5);
        filme3.setPrecoLocacao(10.0);

        filmes.add(filme);
        filmes.add(filme2);
        filmes.add(filme3);

        Locacao locacao = locacaoService.alugarFilme(usuario,filmes);

        error.checkThat(locacao.getValor(), CoreMatchers.is(27.50));

    }

    @Test
    public void teste_desconto_de_50_porcento_no_quarto_filme() throws LocadoraException, FilmeSemEstoqueException {

        Filme filme = new Filme();
        filme.setNome("Os Espiões 1");
        filme.setEstoque(5);
        filme.setPrecoLocacao(10.0);
        Filme filme2 = new Filme();
        filme2.setNome("Os Espiões 2");
        filme2.setEstoque(5);
        filme2.setPrecoLocacao(10.0);
        Filme filme3 = new Filme();
        filme3.setNome("Os Espiões 3");
        filme3.setEstoque(5);
        filme3.setPrecoLocacao(10.0);
        Filme filme4 = new Filme();
        filme4.setNome("Os Espiões 4");
        filme4.setEstoque(5);
        filme4.setPrecoLocacao(10.0);

        filmes.add(filme);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);

        Locacao locacao = locacaoService.alugarFilme(usuario,filmes);

        error.checkThat(locacao.getValor(), CoreMatchers.is(32.50));

    }

    @Test
    public void teste_desconto_de_75_porcento_no_quinto_filme() throws LocadoraException, FilmeSemEstoqueException {

        Filme filme = new Filme();
        filme.setNome("Os Espiões 1");
        filme.setEstoque(5);
        filme.setPrecoLocacao(10.0);
        Filme filme2 = new Filme();
        filme2.setNome("Os Espiões 2");
        filme2.setEstoque(5);
        filme2.setPrecoLocacao(10.0);
        Filme filme3 = new Filme();
        filme3.setNome("Os Espiões 3");
        filme3.setEstoque(5);
        filme3.setPrecoLocacao(10.0);
        Filme filme4 = new Filme();
        filme4.setNome("Os Espiões 4");
        filme4.setEstoque(5);
        filme4.setPrecoLocacao(10.0);
        Filme filme5 = new Filme();
        filme5.setNome("Os Espiões 5");
        filme5.setEstoque(5);
        filme5.setPrecoLocacao(10.0);

        filmes.add(filme);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
        filmes.add(filme5);

        Locacao locacao = locacaoService.alugarFilme(usuario,filmes);

        error.checkThat(locacao.getValor(), CoreMatchers.is(35.00));

    }

    @Test
    public void teste_desconto_de_100_porcento_no_sexto_filme() throws LocadoraException, FilmeSemEstoqueException {

        Filme filme = new Filme();
        filme.setNome("Os Espiões 1");
        filme.setEstoque(5);
        filme.setPrecoLocacao(10.0);
        Filme filme2 = new Filme();
        filme2.setNome("Os Espiões 2");
        filme2.setEstoque(5);
        filme2.setPrecoLocacao(10.0);
        Filme filme3 = new Filme();
        filme3.setNome("Os Espiões 3");
        filme3.setEstoque(5);
        filme3.setPrecoLocacao(10.0);
        Filme filme4 = new Filme();
        filme4.setNome("Os Espiões 4");
        filme4.setEstoque(5);
        filme4.setPrecoLocacao(10.0);
        Filme filme5 = new Filme();
        filme5.setNome("Os Espiões 5");
        filme5.setEstoque(5);
        filme5.setPrecoLocacao(10.0);
        Filme filme6 = new Filme();
        filme6.setNome("Os Espiões 6");
        filme6.setEstoque(5);
        filme6.setPrecoLocacao(10.0);

        filmes.add(filme);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
        filmes.add(filme5);
        filmes.add(filme6);

        Locacao locacao = locacaoService.alugarFilme(usuario,filmes);

        error.checkThat(locacao.getValor(), CoreMatchers.is(35.00));

    }

}
