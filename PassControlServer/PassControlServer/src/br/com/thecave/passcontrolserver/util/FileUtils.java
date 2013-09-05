package br.com.thecave.passcontrolserver.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anton_000
 */
public class FileUtils 
{
    public static boolean saveFile(String path, String content)
    {
        FileWriter writer = null;
        PrintWriter saida = null;
        try 
        {
            writer = new FileWriter(new File(path),true);
            saida = new PrintWriter(writer);
            saida.println(content);
            writer.close();
            saida.close();
            return true;
        } catch (IOException ex) 
        {
         return false;
        }
    }    
}
