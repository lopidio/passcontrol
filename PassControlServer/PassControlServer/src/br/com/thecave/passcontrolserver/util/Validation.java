package br.com.thecave.passcontrolserver.util;

/**
 *
 * @author Arleudo
 */
public class Validation 
{
    public static boolean isDigit( String time )
    {
        for(int i = 0; i < time.length(); i++)
        {
            if(!Character.isDigit(time.toCharArray()[i]))
            {
                return false;
            }
        }
        return true;
    }
    //==============================================================================
}
