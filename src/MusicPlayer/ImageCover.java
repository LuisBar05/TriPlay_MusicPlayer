package MusicPlayer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.jaudiotagger.tag.images.Artwork;

public class ImageCover extends JComponent {

    private BufferedImage cover;
    private int x, y;

    public ImageCover(JPanel panel) {
        this.x = panel.getWidth();
        this.y = panel.getHeight();
        this.setSize(x, y);
    }

    public void setImage(BufferedImage thatImage) {
        this.cover = thatImage;
    }

    public BufferedImage getImage() {
        return cover;
    }

    public BufferedImage getImage(Artwork artwork) throws CannotReadFile, IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(artwork.getBinaryData());
            ImageInputStream iis = ImageIO.createImageInputStream(bais);
            BufferedImage bi = ImageIO.read(iis);
            return bi;
        } catch (IOException ex) {
            throw new CannotReadFile(ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Graphics2D g2d=(Graphics2D) g;
        int scaledWidth = (x * 4) / 5;
        int scaledHeight = (scaledWidth * cover.getHeight()) / cover.getWidth();

        if (cover != null) {
            g.drawImage(cover, 0, 0, x, y, this);
        }

        super.paintComponent(g);
    }

    public void setCover(BufferedImage cover) {
        this.cover = cover;
    }

    public BufferedImage getCover() {
        return cover;
    }
}
