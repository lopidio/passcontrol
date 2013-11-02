package br.com.thecave.passcontrolserver.messages.queuepusher;

import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class QueuePusherUpdateClient extends PassControlMessage
{

    ClientBean clientBean;

    public QueuePusherUpdateClient( ClientBean clientBean )
    {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.clientBean = clientBean;
    }

    public ClientBean getClientBean()
    {
        return clientBean;
    }

    public void setClientBean( ClientBean clientBean )
    {
        this.clientBean = clientBean;
    }
}
