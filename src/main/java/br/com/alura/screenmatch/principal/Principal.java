package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporadas;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String ENDERECO_COM_TEMPORADA= "&season=";
    private final String API_KEY = "&apikey=f7ddf323";
    ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da série que deseja buscar:");
        var nomeDaSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeDaSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporadas> totalTemporadas = new ArrayList<>();

        for (int i = 1; i <= dados.temporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeDaSerie.replace(" ", "+") + ENDERECO_COM_TEMPORADA + i + API_KEY);
            DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
            totalTemporadas.add(dadosTemporadas);
        }
        totalTemporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.temporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada = totalTemporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        totalTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
