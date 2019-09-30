import java.awt.*;
import javax.swing.*;

/**
 * Hintergrundbild vom Startmenu
 *
 * @author Cédric Feuz
 * @since 2019-06-15
 * @version 1.0
 *
 */
class StartmenuBackGround extends JPanel {
   private Image image;
   private boolean fitImage;

   /**
    * Erzeugt ein BackgroundImagePanel, das sich zunächst wie ein "normales" JPanel verhält.
    *
    * @param layout ein LayoutManager zur Anordnung von GUI-Komponenten für das Panel.
    */
   StartmenuBackGround(LayoutManager layout) {
      super(layout);
   }

   /**
    * @see javax.swing.JComponent#paintComponent(Graphics g)
    */
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(image != null) {
         if(fitImage) {
            Dimension size = this.getSize();
            g.drawImage(image, 0, 0, size.width, size.height, this);
         } else {
            g.drawImage(image, 0, 0, this);
         }
      }
   }
   
   /**
    * @see javax.swing.JComponent#getPreferredSize()
    */
   @Override
   public Dimension getPreferredSize() {
      if(image != null) {
         return new Dimension(image.getWidth(this), image.getHeight(this));
      }
      return super.getPreferredSize();
   }
   
   /**
    * Nimmt das zu zeichnende Bild entgegen und nimmt Einstellungen vor, die das Hintergrundbild im Originalformat
    * und in den Original-Dimensionen ausgeben werden.
    *
    * @param image das zu zeichnende Bild
    */
   void setImage(Image image) {
      this.setImage(image, false);
   }

   /**
    * Nimmt das zu zeichnende Bild entgegen und nimmt Einstellungen vor, die das Hintergrundbild im Originalformat
    * oder in Format und Dimension des Panels ausgeben werden.
    *
    * @param image das zu zeichnende Bild
    * @param fitImage bei Übergabe von true wird das Bild immer an die Größe des Panel angepasst (kann zu Verzerrung führen),
    * ansonsten wird immer das Original gezeichnet.
    */
   void setImage(Image image, boolean fitImage) {
      this.image = image;
      this.fitImage = fitImage;
      validate();
      repaint();
   }
}
