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
        String urlF = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String urlS =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(urlF);
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

        for (int i = 0; i < listaDeFilmes.size(); i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nameFile = titulo+".png";
            
            var figurinhas = new Figure();
            figurinhas.create(inputStream, nameFile);
        
            /*System.out.println("\u001b[3mURL da imagem: \u001b[m" + filme.get("image"));
            System.out.println(filme.get("imDbRating"));*/
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numEstrela = (int) classificacao;
            for (int n = 1; n <= numEstrela; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}
