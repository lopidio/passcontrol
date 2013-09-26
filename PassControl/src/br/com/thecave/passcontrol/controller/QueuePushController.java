package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.QueueElementInfo;
import br.com.thecave.passcontrol.screens.QueuePushScreen;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddClient;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientFromRegistration;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientResponse;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

    public ArrayList<ServiceBean> getServices()
    {
        return this.servicos;
    }

    public void loadServices()
    {
        ClientListService listService = new ClientListService(MessageActors.AdministratorActor);
        ClientListServiceResponse response = Main.getInstance().
                getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listService, ClientListServiceResponse.class, 2000);

        if ( response != null )
        {
            servicos = response.getListService();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Comunicação com o servidor interrompida!");
        }
    }

    public void defineServicos( JComboBox cbServico )
    {
        loadServices();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for ( ServiceBean bean : servicos )
        {
            model.addElement(bean.getName());
        }
        cbServico.setModel(model);
    }

    public void insertNewClient( ClientBean bean )
    {
        QueuePusherAddClient addClient = new QueuePusherAddClient(bean);
        ConfirmationResponse response;
        ClientCommunicationThread thread = Main.getInstance().getCommunicationThread();
        response = thread.sendMessageToServerAndWaitForResponseOrTimeout(addClient, ConfirmationResponse.class, 2000);

        if ( response == null )
        {
            JOptionPane.showMessageDialog(null, "Sem resposta do servidor, verifique se o servidor está online!");
        }
        else
        {
            if ( response.getStatusOperation() )
            {
                JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Registro não pode ser salvo!");
            }
        }
    }

    public ClientBean loadRegister( String text )
    {
        QueuePusherLoadClientFromRegistration registration = new QueuePusherLoadClientFromRegistration(text);
        ClientCommunicationThread thread = Main.getInstance().getCommunicationThread();
        QueuePusherLoadClientResponse response = thread.sendMessageToServerAndWaitForResponseOrTimeout(registration, QueuePusherLoadClientResponse.class, 2000);

        if ( response != null )
        {
            ClientBean bean = response.getClientBean();

            if ( bean == null )
            {
                JOptionPane.showMessageDialog(null, response.getComment());
                return null;
            }
            else
            {
                return bean;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Conexão com servidor interrompida!");
            return null;
        }
    }

    public ServiceBean getService( String name )
    {
        for ( ServiceBean serviceBean : servicos )
        {
            if ( serviceBean.getName().equals(name) )
            {
                return serviceBean;
            }
            else
            {
                return null;
            }
        }
        return null;
    }

    public boolean insertNewAtendimento( ClientBean clientBean, UserBean userBean, ServiceBean serviceBean )
    {
        QueuePusherAddQueueElement addQueueElement = new QueuePusherAddQueueElement(clientBean, userBean, serviceBean);
        ClientCommunicationThread thread = Main.getInstance().getCommunicationThread();
        BalconyShowClientMessage response = thread.sendMessageToServerAndWaitForResponseOrTimeout(addQueueElement, BalconyShowClientMessage.class, 2000);

        if ( response == null )
        {
            JOptionPane.showMessageDialog(null, "Conexão com o servidor interrompida!");
            return false;
        }
        else
        {
            // TODO: atualizar o info
            QueueElementInfo elementInfo = new QueueElementInfo(response.getClientName(), 
                                                response.getServiceType(), 
                                                response.getQueuesManagerBean().getPassNumber(),
                                                response.getBalconyNumber());
            screen.showQueueElementInfo(elementInfo);
            return true;
        }
    }
    /**
     * FLUXO: Ao inserir o cliente Envia um: QueuePusherAddQueueElement Caso não
     * exista cliente, setar método setClient(null) A inserção do cliente e do
     * queueelement são feitas automaticamente
     *
     * Retorna um: BalconyShowClientMessage para ser exibido na tela
     * (provavelmente como o QueueelementPanel lá)
     *
     * Para carregar o cliente: Enviar um: QueuePusherLoadClientFromRegistration
     * Retorna um: QueuePusherLoadClientResponse getClientBean será nulo quando
     * não existir nenhum registro desse no banco
     *
     */
}
