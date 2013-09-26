package br.com.thecave.passcontrolserver.util;

/**
 *
 * @author anton_000
 */
public interface IValidation
{
    public boolean valid(String toValid);
    
    public String getComment();
}
