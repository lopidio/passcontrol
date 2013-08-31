package br.com.thecave.passcontrol.db;

import br.com.thecave.passcontrol.db.bean.TableBean;

/**
 * Esta classe que irá se comunicar com o socket
 * as telas não precisam saber que o socket existe
 * basta apenas saber que alguem salva as informações
 * @author Arleudo
 */
public class DataBaseManager 
{
    public static boolean save(TableBean bean)
    {
        return true;
    }    
}
