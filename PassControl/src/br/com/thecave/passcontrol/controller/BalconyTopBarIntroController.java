package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrol.topbar.BalconyTopBarIntro;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalconyResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyTopBarIntroController extends PassControlController
{

    BalconyTopBarIntro topBarIntro = null;
    BalconyInitResponse balconyInitResponse = null;
    private ArrayList<BalconyBean> balconysBeans;

    @Override
    public void initialize()
    {
        BalconyInitRequest balconyInitRequest = new BalconyInitRequest();
        balconyInitResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyInitRequest, BalconyInitResponse.class, 100000);
        balconysBeans = new ArrayList<BalconyBean>();

        if ( balconyInitResponse != null )
        {
            topBarIntro.setBalconyNumbers(balconyInitResponse.getAvaliableBalconys());
            topBarIntro.enableConfirmButton();
        }
    }

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        topBarIntro = (BalconyTopBarIntro) passControlPanel;
    }

    public void confirmButtonPressed( int index )
    {
        BalconyBean selectedBalconyBean = balconyInitResponse.getAvaliableBalconys().get(index);
        BalconyLogin balconyLogin = new BalconyLogin(selectedBalconyBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyLogin, ConfirmationResponse.class, 1000);

        if ( response != null )
        {
            if ( response.getStatusOperation() )
            {
                topBarIntro.blockPassControlTopBar();
                BalconyScreen balconyScreen = (BalconyScreen) Main.getInstance().getMainFrame().getCurrentPassControlPanel();
                balconyScreen.initialize(selectedBalconyBean);
            }
            else
            {
                JOptionPane.showConfirmDialog(null, response.getComment(), "Operação não realizada", JOptionPane.ERROR_MESSAGE);
                //Reinicio o bomboBox
                initialize();
            }
        }
    }

    public void loadBalconys()
    {
        AdministratorListBalcony listBalcony = new AdministratorListBalcony();
        AdministratorListBalconyResponse response = Main.getInstance().
                getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listBalcony, AdministratorListBalconyResponse.class, 2000);

        balconysBeans = response.getBalconyBeans();
    }

    public void defineCBNames( JComboBox cbBalconyName )
    {
        loadBalconys();
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for ( BalconyBean bean : balconysBeans )
        {
            model.addElement(bean.getNumber());
        }
        cbBalconyName.setModel(model);
    }

    public ArrayList<BalconyBean> getBalconyBeans()
    {
        return balconysBeans;
    }

    public void setBalconyBeans( ArrayList<BalconyBean> balconyBeans )
    {
        this.balconysBeans = balconyBeans;
    }
    //Fluxo:
    /**
     * ********** Já no construtor Envio um BalconyInitRequest ao servidor.
     * Apenas para requisitar os números de balcão e os tipos disponíveis Recebo
     * um BalconyInitResponse e inicializo meus dois componentes.
     *
     * **** Ao apertar OK Ao apertar OK, envio um BalconyLogin com as duas
     * informações desta tela ao servidor. Passo para a próxima tela
     */
}
