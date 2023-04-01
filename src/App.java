import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 10 filmes ou séries;
        // System.getenv() para pegar variaveis de ambiente

        // String url =
        // ="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";

        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados para filmes...
        /*
         * for (Map<String, String> filme : listaDeFilmes) {
         * System.out.println(filme.get("title"));
         * System.out.println(filme.get("image"));
         * System.out.println(filme.get("imDbRating"));
         * System.out.println();}
         */
        var diretorio = new File("figure/");
        diretorio.mkdir();
        for (int i = 0; i < listaDeFilmes.size(); i++) {
            Map<String, String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            String textoFigure;
            InputStream avalia;
            if (classificacao >= 8.0) {
                textoFigure = "TOPZERA!!!";
                avalia = new FileInputStream(new File("sobrepor/like.png"));
            } else {
                textoFigure = "HMMMMM...";
                avalia = new FileInputStream(new File("sobrepor/hmmm.png"));
            }
            InputStream inputStream = new URL(urlImagem).openStream();
            String nameFile = "figure/" + titulo + ".png";

            var figurinhas = new Figure();
            figurinhas.create(inputStream, nameFile, textoFigure, avalia);

            /*
             * System.out.println("\u001b[3mURL da imagem: \u001b[m" + filme.get("image"));
             * System.out.println(filme.get("imDbRating"));
             */

            int numEstrela = (int) classificacao;
            for (int n = 1; n <= numEstrela; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}
