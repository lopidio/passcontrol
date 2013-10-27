package br.com.thecave.passcontrol.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Printer implements Printable
{
    private String senha;
    private String service;
    private String nome;
    
    public Printer(String senha, String servico, String nome)
    {
        this.senha = senha;
        this.service = servico;
        this.nome = nome;
    }
    
    @Override
    public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException
    {
        if (page > 0)
        { /* We have only one page, and 'page' is zero-based */
                return NO_SUCH_PAGE;
        }

        /*
         * User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
        g.drawString("Moinho Cearense", 0, 10);
        g.drawString("Senha:", 0, 30);
        g.drawString(this.senha, 20, 43);
        g.drawString("Serviço:", 0, 60);
        g.drawString(this.service, 20, 73);
        if(!this.nome.equals(""))
        {
            g.drawString("Nome:", 0, 90);
            g.drawString(this.nome, 20, 103);
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
}
