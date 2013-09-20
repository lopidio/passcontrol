package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.QueuePushScreen;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePushController extends PassControlController
{

    QueuePushScreen screen;
    ArrayList<ServiceBean> servicos;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (QueuePushScreen) passControlPanel;
        servicos = new ArrayList<>();
    }

    public void insert()
    {
    }    
    
    public void loadServices()
    {
        ClientListService listService = new ClientListService(MessageActors.AdministratorActor);
        ClientListServiceResponse response = Main.getInstance().
                getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listService, ClientListServiceResponse.class, 2000);

        servicos = response.getListService();
    }

    public void defineServicos( JComboBox cbServico )
    {
        loadServices();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(ServiceBean bean : servicos)
        {
            model.addElement(bean.getName());
        }
        cbServico.setModel(model);
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
