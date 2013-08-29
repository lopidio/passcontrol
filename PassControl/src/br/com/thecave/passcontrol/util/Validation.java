package br.com.thecave.passcontrol.util;

/**
 *
 * @author Arleudo
 */
public class Validation 
{
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
    
    public static ErrorsField validStringSize(String src, int minSize, int maxSize)
    {
        if(src.length() > maxSize)
            return ErrorsField.ERROR_MAX_SIZE;
        else
            if(src.length() < minSize)
                return ErrorsField.ERROR_MIN_SIZE;
        return ErrorsField.SUCCESS_VALIDATION;
    }    
}

