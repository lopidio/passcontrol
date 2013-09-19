package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.QueuePushScreen;
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
