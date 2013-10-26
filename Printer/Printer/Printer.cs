﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;


namespace Printer
{
    class Printer
    {
        //Configura o modelo da impressora
        [DllImport("MP2032.dll")]
        public static extern int ConfiguraModeloImpressora(int modelo);
        //Inicia Porta
        [DllImport("MP2032.dll")]
        public static extern int IniciaPorta(String porta);
        //Enviar texto formatado
        [DllImport("MP2032.dll")]
        public static extern int FormataTX(String texto, int TipoLetra, int italico, int sublinhado, int expandido, int enfatizado);
        //Enviar comandos para a impressora
        [DllImport("MP2032.dll")]
        public static extern int ImprimeBitmap(String fileName, int modo);
        //Fecha a porta
        [DllImport("MP2032.dll")]
        public static extern int FechaPorta();

        [DllImport("MP2032.dll")]
        public static extern int AcionaGuilhotina(int mod);

        [DllImport("MP2032.dll")]
        public static extern int HabilitaEsperaImpressao(int mod);

        [DllImport("MP2032.dll")]
        public static extern void EsperaImpressao();

        [DllImport("MP2032.dll")]
        public static extern int ComandoTX(String comando, int tComando);

        [DllImport("MP2032.dll")]
        public static extern int ConfiguraCodigoBarras(int comando, int tComando, int a, int b, int c);

        [DllImport("MP2032.dll")]
        public static extern int ImprimeCodigoBarrasCODE128(String cod);
        

        static void Main(string[] args)
        {
            if (args == null)
            {
                // Do nothing
            }
            else
            {
                int iRetorno = 0;

                iRetorno = Printer.ConfiguraModeloImpressora(8);
                
                //Abrindo a porta
                iRetorno = Printer.IniciaPorta("COM5");

                iRetorno = Printer.ConfiguraCodigoBarras(102, 0, 0, 0, 10);

                iRetorno = Printer.ImprimeCodigoBarrasCODE128("01234567890123456789012345678901234567890123");

                // imprimindo a imagem
                //iRetorno = Printer.ImprimeBitmap("config\\imgToPrint.bmp", 1);

                // colocando espaços em branco
                string s_cmdTX = "\r\n\r\n\r\n\r\n\r\n";
                
                // Comando para salto de linha
                iRetorno = Printer.ComandoTX(s_cmdTX, s_cmdTX.Length);

                // aciona a guilhotina
                //iRetorno = Printer.AcionaGuilhotina(0);

                // imprime a segunda via
                //iRetorno = Printer.ImprimeBitmap("config\\imgToPrint.bmp", 1);

                // salta linhas
                //iRetorno = Printer.ComandoTX(s_cmdTX, s_cmdTX.Length);

                // aciona a guilhotina
                iRetorno = Printer.AcionaGuilhotina(0);
                
                //Fechar a porta utilizada
                iRetorno = Printer.FechaPorta();
    
                // and voilá                
            }            
        }
    }
}
