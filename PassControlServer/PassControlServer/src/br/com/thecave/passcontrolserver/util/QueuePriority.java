/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

/**
 *
 * @author lopidio
 */
public class QueuePriority 
{
    public static enum QueuePriorityEnum
    {
        QueuePriorityMaxima,
        QueuePriorityAlta,
        QueuePriorityMedia,
        QueuePriorityBaixa,
        QueuePriorityMinima
    }
    
    public static int convertEnumToInt(QueuePriorityEnum queuePriorityEnum)
    {
        switch (queuePriorityEnum)
        {
            case QueuePriorityMaxima:
                return 5;
            case QueuePriorityAlta:
                return 4;
            case QueuePriorityMedia:
                return 3;
            case QueuePriorityBaixa:
                return 2;
            case QueuePriorityMinima:
                return 1;
            default:
                return 0;
        }
    }
    
    public static QueuePriorityEnum convertIntToEnum(int queuePriorityInt)
    {
        switch (queuePriorityInt)
        {
            case 5:
                return QueuePriorityEnum.QueuePriorityMaxima;
            case 4:
                return QueuePriorityEnum.QueuePriorityAlta;
            case 3:
                return QueuePriorityEnum.QueuePriorityMedia;
            case 2:
                return QueuePriorityEnum.QueuePriorityBaixa;
            case 1:
                return QueuePriorityEnum.QueuePriorityMinima;
            default:
                return QueuePriorityEnum.QueuePriorityMedia;
        }
    }
}
