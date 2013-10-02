package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.component.util.QueueElementInfo;
import br.com.thecave.passcontrol.controller.QueuePopController;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public final class QueuePopScreen extends PassControlPanel
{
    ArrayList<ArrayList<QueueElementInfo>> queueElementInfos;
    ArrayList<Box> queuesBoxArea;
    
    QueuePopController controller = null;

    /**
     * Creates new form AdminScreen
     */
    public QueuePopScreen()
    {
        super("Controle de Fila", new QueuePopController());
        this.controller = (QueuePopController) getPanelController();
        initComponents();
        
        jpScrollablePane.setLayout(new BoxLayout(jpScrollablePane, BoxLayout.X_AXIS)); //Talvez esse Layout seja o mais adequado
        jpScrollablePane.setAlignmentY(TOP_ALIGNMENT);
//        jpScrollablePane.setLayout(new FlowLayout());        

        //5 é quantidade de prioridades
        queuesBoxArea = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) 
        {
            Box newBox = Box.createVerticalBox();
//            newBox.setForeground(Color.red);
//            Box newBox = Box.createHorizontalBox();
//            newBox.setLayout(new BoxLayout(newBox, BoxLayout.Y_AXIS));
//            newBox.setAlignmentX(LEFT_ALIGNMENT);
            queuesBoxArea.add(newBox);
        }
        queueElementInfos = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) 
        {
            queueElementInfos.add(new ArrayList<QueueElementInfo>());
        }
        
        jpScrollablePane.add(Box.createHorizontalStrut(30));        
        for (int i = 0; i < 5; i++) 
        {
            jpScrollablePane.add(queuesBoxArea.get(i));
            jpScrollablePane.add(Box.createHorizontalStrut(30));
            
            
            //Aproveito o for para instanciar isso aqui também
            queueElementInfos.set(i, new ArrayList<QueueElementInfo>());
            
        }
        
        insertDefaultQueuesInfo();
      
    }

    public ArrayList<ArrayList<QueueElementInfo>> getQueueElementInfos() {
        return queueElementInfos;
    }

    public QueueElementInfo findQueueElementInfoFromPassNumber(String passNumber)
    {
        for (ArrayList<QueueElementInfo> arrayList : queueElementInfos) 
        {
            for (QueueElementInfo queueElementInfo : arrayList) 
            {
                //Se for esse mesmo
                if (queueElementInfo.getUserPass().equals(passNumber))
                {
                    return queueElementInfo;
                }
            }
        }
        return null;
    }

    public void disableAllQueueElementInfo()
    {
        for (ArrayList<QueueElementInfo> arrayList : queueElementInfos) 
        {
            for (QueueElementInfo queueElementInfo : arrayList) 
            {
                queueElementInfo.setEnabled(false);
            }
        }    
    }    
    
    public ArrayList<Box> getQueuesBoxArea() {
        return queuesBoxArea;
    }

    public JPanel getJpScrollablePane() {
        return jpScrollablePane;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOutterScrollPane = new javax.swing.JScrollPane();
        jpScrollablePane = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jOutterScrollPane.setViewportView(jpScrollablePane);

        add(jOutterScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1340, 680));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jOutterScrollPane;
    private javax.swing.JPanel jpScrollablePane;
    // End of variables declaration//GEN-END:variables

    public void clearAllQueues() 
    {
        insertDefaultQueuesInfo();
        //Limpo todos os exibidores de fila
        for (Box box : queuesBoxArea)
        {
            box.removeAll();
            box.repaint();
            box.revalidate();
        }
        for (ArrayList<QueueElementInfo> arrayList : queueElementInfos) 
        {
            //Removo todos os queue que tenho
            arrayList.clear();
        }
    }
    
    public void insertDefaultQueuesInfo()
    {
        String[] queuePriority = {"Mínima", "Baixa", "Média", "Alta", "Máxima"};
        for (int i = 0; i < 5; i++) 
        {
            //Crio um QueueInfoPanel        
            QueueElementInfo queueElementInfo = new QueueElementInfo("", queuePriority[i], "", "");
            queueElementInfo.setEnabled(false);
            //Aramazeno na fila correta
            Box queueAreaToAdd = queuesBoxArea.get(i);
            queueAreaToAdd.add(queueElementInfo);
            queueAreaToAdd.revalidate();
            queueAreaToAdd.repaint();            
        }
    }

}
