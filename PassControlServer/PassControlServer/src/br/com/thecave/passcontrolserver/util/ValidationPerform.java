package br.com.thecave.passcontrolserver.util;

import java.util.ArrayList;

/**
 *
 * @author Arleudo
 */
public class ValidationPerform
{
    private static String coment = "";
    
    public static boolean valid(String toValid, ArrayList<IValidation> validations)
    {
        for ( IValidation validation : validations )
        {
            if(!validation.valid(toValid))
            {
                coment = validation.getComment();
                return false;
            }
        }
        return true;
    }
    
    public static String getComment()
    {
        return coment;
    }
}
