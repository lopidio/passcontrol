/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import javax.swing.ImageIcon;

/**
 *
 * @author lopidio
 */
public class PrinterPrintImageMessage extends PassControlMessage
{
    ImageIcon imageIcon;

    public PrinterPrintImageMessage(ImageIcon imageIcon)
    {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
    
    
    
    
}
