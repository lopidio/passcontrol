package br.com.thecave.passcontrolserver.util.validations;

import br.com.thecave.passcontrolserver.util.IValidation;

/**
 *
 * @author Antonio Arleudo
 */
public class ValidIsEmpty implements IValidation
{
    @Override
    public boolean valid( String toValid )
    {
        if(toValid.equals(""))
            return false;
        else
            return true;
    }

    @Override
    public String getComment()
    {
        return "Campo não pode ser em branco!";
    }
    
}
