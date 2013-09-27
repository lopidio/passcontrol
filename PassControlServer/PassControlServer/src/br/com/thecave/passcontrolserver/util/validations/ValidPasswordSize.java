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
public class ValidPasswordSize implements IValidation
{
    private String comment;
    
    @Override
    public boolean valid( String toValid )
    {
        if( toValid.length() < 6 )
        {
            this.comment = "Tamanho deve ser maior ou igual a 6 caracteres!";
            return false;
        }
        return true;
    }
    
    @Override
    public String getComment()
    {
        return this.comment;
    }    
}
