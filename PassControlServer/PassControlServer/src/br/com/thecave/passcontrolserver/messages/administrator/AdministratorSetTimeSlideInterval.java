package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class AdministratorSetTimeSlideInterval extends PassControlMessage
{
    private int timeInterval;
    
    public AdministratorSetTimeSlideInterval(int time) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.timeInterval = time;
    }

    /**
     * @return the timeInterval
     */
    public int getTimeInterval()
    {
        return timeInterval;
    }

    /**
     * @param timeInterval the timeInterval to set
     */
    public void setTimeInterval( int timeInterval )
    {
        this.timeInterval = timeInterval;
    }
    
}
