import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa   {
    public List<Conteudo> extraiConteudo(String json) {
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        // JEITO SIMPLES
        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url"))).toList();

        /*
         * JEITO COMPLICADO
         * .map((Map<String, String> atributos) -> {
         * String titulo = atributos.get("title");
         * String urlImagem = atributos.get("hdurl");
         * var conteudo = new Conteudo(titulo, urlImagem);
         * 
         * })
         * .toList();
         */
        /*
         * JEIYO ANTERIOR
         * List<Conteudo> conteudos = new ArrayList<>();
         * 
         * // popular a lista de conteudos
         * for (Map<String, String> atributos : listaDeAtributos) {
         * String titulo = atributos.get("title");
         * String urlImagem = atributos.get("hdurl");
         * 
         * var conteudo = new Conteudo(titulo, urlImagem);
         * 
         * conteudos.add(conteudo);
         * }
         * 
         * return conteudos;
         */
    }
}
