package br.com.thecave.passcontrol.utils;

import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Printer
{
    public void generateImg( JPanel comp ) throws Exception
    {
        String caminho = "config\\imgToPrint.bmp";
        Dimension size = comp.getSize();
        BufferedImage image = new BufferedImage(
                    size.width, size.height 
                              , BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2 = image.createGraphics();
        comp.repaint();
        comp.paint(g2);
        try
        {
            ImageIO.write(image, "bmp", new File(caminho));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void imprimirArquivo( )
    {
        String porta = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile().getPortPrinter();
        try
        {
            Runtime.getRuntime().exec("Printer " + porta);
        }
        catch ( IOException ex )
        {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
