import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Figure {

    public void create() throws Exception {
        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(new File("entrada/filme.jpg", null));

        // criar nova imagem em memória com transparência e com tamanho novo
        int largura = originalImage.getWidth();
        int altura = originalImage.getHeight();
        int novaAltura = altura + 200;
        BufferedImage newImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova (na memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null, null);
        // escrever uma frase na nova imagem

        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("saida/figurinha.png"));
    }

    public static void main(String[] args) throws Exception {
        var generator = new Figure();
        generator.create();
    }
}