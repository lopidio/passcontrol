/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class PassControlFont
{
    /**
     * Fonte em si
     */
    Font font;

    public PassControlFont() 
    {
        try 
        {
            InputStream is = getClass().getResourceAsStream("/resources/Square721 BT Roman.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);        
        } catch (FontFormatException ex) {
            Logger.getLogger(PassControlFont.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PassControlFont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Font getFont()
    {
        return font;
    }

    public Font getSizedFont(float size)
    {
        return font.deriveFont(size);
    }
    
}
