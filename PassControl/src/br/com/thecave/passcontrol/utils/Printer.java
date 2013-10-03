package br.com.thecave.passcontrol.utils;

import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
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
        BufferedImage image = new BufferedImage((int)(comp.getWidth()* 0.6), (int)(comp.getHeight()* 0.6),
        BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.scale(0.6, 0.6);
        comp.paint(graphics2D);
        
        ImageIO.write(image,"bmp", new File(caminho));
    }

    public void imprimirArquivo( )
    {
        String porta = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile().getPortPrinter();
        try
        {
            Runtime.getRuntime().exec(porta);
        }
        catch ( IOException ex )
        {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
