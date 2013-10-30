package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.QueueElementInfoBig;
import br.com.thecave.passcontrol.screens.ViewerScreen;
import br.com.thecave.passcontrol.viewer.PresentationControler;
import br.com.thecave.passcontrol.viewer.PresentationControllerObserver;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfigurationFileAlterationMessage;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.RequestConfigurationFile;
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
        
        //Escutar essa aqui também: AdministratorAddSlideAnimation e derivados
        communicationThread.addMessageListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        
        RequestConfigurationFile requestConfigurationFile = new RequestConfigurationFile();
        ConfigurationFileAlterationMessage message = Main.getInstance().getCommunicationThread().
                sendMessageToServerAndWaitForResponseOrTimeout(requestConfigurationFile, ConfigurationFileAlterationMessage.class, 10000);

        //Se eu obtiver resposta, pego a resposta
        ConfigurationFile configurationFile;
        if (message != null)
        {
            configurationFile = message.getConfigurationFile();
        }
        else //Se não, pego o já salvo na minha máquina
        {
            configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
        }
        
        presentationControler = new PresentationControler();
        presentationControler.addObserver(this);
        presentationControler.setTime(configurationFile.getSlideShowSpeed());
        for ( Map.Entry<String, ImageIcon> slides : configurationFile.getImgsSlide().entrySet() )
        {
            presentationControler.addImage(slides.getValue().getImage());
        }
        System.out.println("Quantidade de slides da animação: " + presentationControler.getImages().size());
        
        //Adiciono a primeira imagem
        screen.setPresentationImage(presentationControler.getCurrentImage());        
        new Thread(presentationControler).start();
    }

    @Override
    public void removeMessageListeners()
    {
        super.removeMessageListeners();
        ClientCommunicationThread communicationThread = Main.getInstance().getCommunicationThread();
        //BalconyShowClientMessage received = (BalconyShowClientMessage) message;
        communicationThread.removeListener(this, BalconyShowClientMessage.class);
        presentationControler.stop();
    }

    @Override
    public void onMessageReceive( PassControlMessage message, Socket socket )
    {
        super.onMessageReceive(message, socket);

        BalconyShowClientMessage received = (BalconyShowClientMessage) message;

        
        //Se eu for o destino da mensagem
        if (received.getTo() == MessageActors.ViewerActor)
        {        
            QueueElementInfoBig elementInfoBig = new QueueElementInfoBig(received.getClientName(), 
                                                                         received.getServiceType(), 
                                                                         received.getQueuesManagerBean().getPassNumber(),
                                                                         received.getBalconyNumber());

            screen.addQueueElementInfo(elementInfoBig);        
        }
    }

    @Override
    public void onPresentationChange( Image img )
    {
        screen.setPresentationImage(img);
    }
}
