package br.com.thecave.passcontrol.utils;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.generic.PrinterPrintImageMessage;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Printer implements PassControlMessageListener
{

    public Printer()
    {
        //do nothing
    }
    
    public void initialize()
    {
        Main.getInstance().getCommunicationThread().addMessageListener(this, PrinterPrintImageMessage.class);
    }
    
    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket)
    {
        PrinterPrintImageMessage printerPrintImageMessage = (PrinterPrintImageMessage)message;
        
        //Crio uma BufferedImagem a partir da imagem recebida
        Image image = printerPrintImageMessage.getImageIcon().getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();        
        
        //Salva a imagem no local correto
        saveImageToPrinterFile(bufferedImage);
        
        //Imprime a imagem adequada
        printPrinterFile();

    }
    
    public static BufferedImage generateImageFromComponent( JPanel comp ) throws Exception
    {
        Dimension size = comp.getSize();
        BufferedImage image = new BufferedImage(
                    size.width, size.height 
                              , BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2 = image.createGraphics();
        comp.repaint();
        comp.paint(g2);

        return image;
    }

    public void printPrinterFile( )
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

    public void saveImageToPrinterFile(BufferedImage image)
    {
        String caminho = "config\\imgToPrint.bmp";
        try
        {
            ImageIO.write(image, "bmp", new File(caminho));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
