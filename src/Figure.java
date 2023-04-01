import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Figure {

  public void create(InputStream inputStream, String nameFile, String texto, InputStream InputStreamimagemS)
      throws Exception {
    // leitura da imagem
    // InputStream inputStream = new FileInputStream(new File("D:/Documentos
    // (D)/VSCodeJava/aluraStickers/2-ImersaoJava/entrada/filme.jpg"));
    // InputStream inputStream = new URL(
    // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
    BufferedImage originalImage = ImageIO.read(inputStream);

    // criar nova imagem em memória com transparência e com tamanho novo
    int largura = originalImage.getWidth();
    int altura = originalImage.getHeight();
    int novaAltura = altura + 200;
    BufferedImage newImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para a nova (na memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null, null);
    BufferedImage imagemS = ImageIO.read(InputStreamimagemS);
    int posicaoYImagemS = novaAltura - imagemS.getHeight();
    graphics.drawImage(imagemS, 0, posicaoYImagemS, null, null);

    // configurar fonte
    var fonte = new Font("Impact", Font.BOLD, 150);
    graphics.setFont(fonte);
    graphics.setColor(Color.YELLOW);

    // escrever uma frase na nova imagem
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoXTexto = (largura - larguraTexto) / 2;
    int posicaoYTexto = novaAltura - 50;
    graphics.drawString(texto, posicaoXTexto, posicaoYTexto);

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    var textLayout = new TextLayout(texto, fonte, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoXTexto, posicaoYTexto);
    graphics.setTransform(transform);

    var outLineStroke = new BasicStroke(largura * 0.0041f);
    graphics.setStroke(outLineStroke);

    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);

    // escrever a nova imagem em um arquivo
    ImageIO.write(newImage, "png",
        new File(nameFile));
  }

}