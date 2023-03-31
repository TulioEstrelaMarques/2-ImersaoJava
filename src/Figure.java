import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Figure {

    public void create(InputStream inputStream, String nameFile) throws Exception {
        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("D:/Documentos
        // (D)/VSCodeJava/aluraStickers/2-ImersaoJava/entrada/filme.jpg"));
        //InputStream inputStream = new URL(
          //      "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // criar nova imagem em memória com transparência e com tamanho novo
        int largura = originalImage.getWidth();
        int altura = originalImage.getHeight();
        int novaAltura = altura + 200;
        BufferedImage newImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova (na memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null, null);
        // configurar fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // escrever uma frase na nova imagem
        graphics.drawString("Nunca Vi!!!", 100, novaAltura - 100);
        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png",
                new File(nameFile));
    }

}