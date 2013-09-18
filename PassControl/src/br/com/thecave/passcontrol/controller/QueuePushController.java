package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.QueuePushScreen;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientFromRegistration;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientResponse;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePushController extends PassControlController
{

    QueuePushScreen screen;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (QueuePushScreen) passControlPanel;
    }

    public void insert()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * FLUXO:
     * Ao inserir o cliente
     * Envia um: QueuePusherAddQueueElement
     *  Caso não exista cliente, setar método setClient(null)
     * A inserção do cliente e do queueelement são feitas automaticamente
     * 
     * Retorna um: BalconyShowClientMessage para ser exibido na tela (provavelmente como o QueueelementPanel lá)
     * 
     * Para carregar o cliente:
     * Enviar um: QueuePusherLoadClientFromRegistration
     * Retorna um: QueuePusherLoadClientResponse
     *  getClientBean será nulo quando não existir nenhum registro desse no banco
     * 
     */
}
