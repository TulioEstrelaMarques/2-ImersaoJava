import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 10 filmes ou séries;
        // System.getenv() para pegar variaveis de ambiente

        String url =

                // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

               // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

         "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";

        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";

        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorConteudo extrator = new ExtratorConteudoNasa();
        // ExtratorConteudo extrator = new ExtratorConteudoImdb();

        List<Conteudo> conteudos = extrator.extraiConteudo(json);

        // exibir e manipular os dados para imagem...
        /*
         * for (Map<String, String> filme : listaDeFilmes) {
         * System.out.println(filme.get("title"));
         * System.out.println(filme.get("image"));
         * System.out.println(filme.get("imDbRating"));
         * System.out.println();}
         */
        var diretorio = new File("figure/");
        diretorio.mkdir();
        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo = conteudos.get(i);

            double classificacao = 8;// ouble.parseDouble(conteudo.get("imDbRating"));
            String textoFigure;
            InputStream avalia;

            if (classificacao >= 8.0) {
                textoFigure = "TOPZERA!!!";
                avalia = new FileInputStream(new File("sobrepor/like.png"));
            } else {
                textoFigure = "HMMMMM...";
                avalia = new FileInputStream(new File("sobrepor/hmmm.png"));
            }
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nameFile = "figure/" + conteudo.titulo() + ".png";

            var figurinhas = new Figure();
            figurinhas.create(inputStream, nameFile, textoFigure, avalia);

            /*
             * System.out.println("\u001b[3mURL da imagem: \u001b[m" + filme.get("image"));
             * System.out.println(filme.get("imDbRating"));
             */
            System.out.println(conteudo.titulo());
            int numEstrela = (int) classificacao;
            for (int n = 1; n <= numEstrela; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}
