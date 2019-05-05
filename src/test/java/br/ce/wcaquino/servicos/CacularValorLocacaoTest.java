package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.exceptions.NaoPodeAlugarFilmeDeDomingoException;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CacularValorLocacaoTest {

    private LocacaoService locacaoService;

    @Parameterized.Parameter
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valor;

    @Parameterized.Parameter(value = 2)
    public String descricao;

    public Usuario usuario;

    public ErrorCollector error = new ErrorCollector();

    private static Filme filme1 = new Filme("Os Espiões 1",5,10.0);
    private static Filme filme2 = new Filme("Os Espiões 2",5,10.0);
    private static Filme filme3 = new Filme("Os Espiões 3",5,10.0);
    private static Filme filme4 = new Filme("Os Espiões 4",5,10.0);
    private static Filme filme5 = new Filme("Os Espiões 5",5,10.0);
    private static Filme filme6 = new Filme("Os Espiões 6",5,10.0);


    @Before
    public void before(){
        locacaoService = new LocacaoService();
        usuario = new Usuario();
        usuario.setNome("Luiz");
    }

    @Parameterized.Parameters(name = "Teste {index} : Valor={1} / QtdFilmes={2}")
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1,filme2,filme3), 27.50, "3 Filmes"},
                {Arrays.asList(filme1,filme2,filme3, filme4), 32.50, "4 Filmes"},
                {Arrays.asList(filme1,filme2,filme3, filme4, filme5), 35.00, "5 Filmes"},
                {Arrays.asList(filme1,filme2,filme3, filme4, filme5, filme6), 35.00, "6 Filmes"}
        });
    }


    @Test
    public void teste_desconto_filme() throws LocadoraException, FilmeSemEstoqueException, NaoPodeAlugarFilmeDeDomingoException {

        Locacao locacao = locacaoService.alugarFilme(usuario,filmes);

        error.checkThat(locacao.getValor(), CoreMatchers.is(valor));

    }
}
