package br.com.thecave.passcontrol.util;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Arleudo
 */
public class Validation 
{
    //==============================================================================
    public enum ErrorsField 
    {
        ERROR_MIN_SIZE("Tamanho mínimo não atingido!"),
        ERROR_MAX_SIZE("Tamanho máximo ultrapassado!"),
        SUCCESS_VALIDATION("Campo validado com sucesso!");
    
        private ErrorsField(final String text) 
        {
            this.text = text;
        }

        private final String text;

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() 
        {
            return text;
        }
    }
    //==============================================================================
    public static ErrorsField validStringSize(String src, int minSize, int maxSize)
    {
        if(src.length() > maxSize)
            return ErrorsField.ERROR_MAX_SIZE;
        else
            if(src.length() < minSize)
                return ErrorsField.ERROR_MIN_SIZE;
        return ErrorsField.SUCCESS_VALIDATION;
    }  
    //==============================================================================
    /**
     * Valida um campo de string e seta o label de erro
     * @param field TextField contendo a string a ser validada
     * @param label Label que ira mostrar o erro
     */
    public static boolean validarCampo(JTextField field, JLabel label)
    {
        String name = field.getText();
        int minSize = 4;
        int maxSize = 30;
        
        ErrorsField ret = Validation.validStringSize( name, minSize, maxSize ) ;
        
        if(ret == ErrorsField.SUCCESS_VALIDATION)
        {
            label.setVisible(false);
            return true;
        } 
        else if(ret == ErrorsField.ERROR_MAX_SIZE)
        {
            label.setVisible(true);
            label.setToolTipText(ErrorsField.ERROR_MAX_SIZE.toString());
            return false;
        }
        else
        {
            label.setVisible(true);
            label.setToolTipText(ErrorsField.ERROR_MIN_SIZE.toString());
            return false;
        }
    }
    //==============================================================================
}

