/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util.validations;

import br.com.thecave.passcontrolserver.util.IValidation;

/**
 *
 * @author Antonio Arleudo
 */
public class ValidIsDigit implements IValidation
{
    private String comment;
    
    @Override
    public boolean valid( String toValid )
    {
        for(int i = 0; i < toValid.length(); i++)
        {
            if(!Character.isDigit(toValid.toCharArray()[i]))
            {
                this.comment = "Somente dígitos são permitidos!";
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String getComment()
    {
        return this.comment;
    }    
}
