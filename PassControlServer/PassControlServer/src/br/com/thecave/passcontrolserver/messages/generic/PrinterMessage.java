/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author lopidio
 */
public class PrinterMessage extends PassControlMessage
{
    private String senha;
    private String servico;
    private String nome;
    
    public PrinterMessage(String senha, String servico, String nome)
    {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.nome = nome;
        this.senha = senha;
        this.servico = servico;
    }

    /**
     * @return the senha
     */
    public String getSenha()
    {
        return senha;
    }

    /**
     * @return the servico
     */
    public String getServico()
    {
        return servico;
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }
    
}
