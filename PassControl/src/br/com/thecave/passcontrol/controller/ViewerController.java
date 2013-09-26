package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.ViewerScreen;
import br.com.thecave.passcontrol.viewer.PresentationControler;
import br.com.thecave.passcontrol.viewer.PresentationControllerObserver;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.util.ConfigurationFile;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import java.awt.Image;
import java.net.Socket;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ViewerController extends PassControlController implements PresentationControllerObserver
{
    ViewerScreen screen;
    PresentationControler presentationControler;
    

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (ViewerScreen) passControlPanel;
    }  

    @Override
    public void addMessageListeners()
    {
        super.addMessageListeners();
        ClientCommunicationThread communicationThread = Main.getInstance().getCommunicationThread();
        //BalconyShowClientMessage received = (BalconyShowClientMessage) message;
        communicationThread.addMessageListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
        presentationControler = new PresentationControler();
        presentationControler.setTime(configurationFile.getSlideShowSpeed());
        for ( Map.Entry<String, ImageIcon> en : configurationFile.getImgsSlide().entrySet() )
        {
            presentationControler.addImage(en.getValue().getImage());
        }
    }

    @Override
    public void removeMessageListeners()
    {
        super.removeMessageListeners();
        ClientCommunicationThread communicationThread = Main.getInstance().getCommunicationThread();
        //BalconyShowClientMessage received = (BalconyShowClientMessage) message;
        communicationThread.removeListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void onMessageReceive( PassControlMessage message, Socket socket )
    {
        super.onMessageReceive(message, socket);
    }

    @Override
    public void onChange( Image img )
    {
        screen.setPresentationImage(img);
    }
}
