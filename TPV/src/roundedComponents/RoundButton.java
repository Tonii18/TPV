package roundedComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundButton extends JButton {

    private int arcWidth;
    private int arcHeight;
    private Color borderColor; // Variable para almacenar el color del borde

    public RoundButton(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setFocusPainted(false);  // Evitar borde de enfoque
        setContentAreaFilled(false);  // Evitar relleno predeterminado
        setBorderPainted(false);  // Evitar el borde predeterminado
        setOpaque(false);  // Evitar artefactos visuales no deseados
    }

    // Método para establecer el color del borde
    public void setCustomBorderColor(Color color) {
        this.borderColor = color;
        repaint(); // Actualizar la pintura del botón cuando se cambie el color
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo del botón
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        // Dibujar el texto del botón
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1)); // Definir el grosor del borde

            // Dibujar el borde redondeado
            g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, arcWidth, arcHeight));
            g2.dispose();
        }
    }
}
