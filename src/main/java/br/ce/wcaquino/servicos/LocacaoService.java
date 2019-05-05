package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.exceptions.NaoPodeAlugarFilmeDeDomingoException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws LocadoraException, FilmeSemEstoqueException, NaoPodeAlugarFilmeDeDomingoException {

		if(usuario == null)
			throw new LocadoraException("Usuario vazio!");

		if(filmes == null || filmes.isEmpty())
			throw new LocadoraException("Filme vazio!");

		for (Filme filme: filmes) {
			if(filme.getEstoque() == 0)
				throw new FilmeSemEstoqueException("Filme sem estoque!");
		}

		if(filmes.size() >= 3)
		    filmes.get(2).setPrecoLocacao(filmes.get(2).getPrecoLocacao()*0.75);

        if(filmes.size() >= 4)
            filmes.get(3).setPrecoLocacao(filmes.get(3).getPrecoLocacao()*0.50);

        if(filmes.size() >= 5)
            filmes.get(4).setPrecoLocacao(filmes.get(4).getPrecoLocacao()*0.25);

        if(filmes.size() >= 6)
            filmes.get(5).setPrecoLocacao(0.0);



		Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(verificaDiaAtual());

		calculaPrecoTotal(filmes);
		locacao.setValor(calculaPrecoTotal(filmes));

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	private Double calculaPrecoTotal(List<Filme> filmes) {

		Double precoTotal = 0.0;
		for (Filme filme: filmes) {
			precoTotal = precoTotal + filme.getPrecoLocacao();
		}
		return  precoTotal;
	}

	private Date verificaDiaAtual() throws NaoPodeAlugarFilmeDeDomingoException{
	    if(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY)){
            throw new NaoPodeAlugarFilmeDeDomingoException("Domingo nao pode alugar filme");
        }
	    return new Date();
    }

}