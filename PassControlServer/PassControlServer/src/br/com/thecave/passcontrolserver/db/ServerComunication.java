package br.com.thecave.passcontrolserver.db;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.BalconyTypesBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.bean.TableBean;
import br.com.thecave.passcontrolserver.db.bean.UserBean;

/**
 * Esta classe que irá se comunicar com o socket
 * as telas não precisam saber que o socket existe
 * basta apenas saber que alguem salva as informações
 * @author Arleudo
 */
public class ServerComunication 
{
    public static boolean save(TableBean bean)
    {
        return true;
    }
    
    public static boolean delete(TableBean bean)
    {
        return true;
    }
    
    public static UserBean selectUser(int index)
    {
        return new UserBean();
    }
    
    public static ServiceBean selectService(int index)
    {
        return new ServiceBean();
    }
    
    public static BalconyBean selectBalcony(int index)
    {
        return new BalconyBean();
    }
    
    public static BalconyTypesBean selectBalconyTypes(int index)
    {
        return new BalconyTypesBean();
    }
}
