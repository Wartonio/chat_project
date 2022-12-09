
package classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.*;
import java.awt.*;





public class Serveur extends JFrame {

    public Serveur() {
        initComponents();
    }
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    

    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        jScrollPane1 = new JScrollPane();
        msg_area = new JTextArea();
        msg_txt = new JTextField();
        msg_send = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        msg_area.setColumns(40);                                                                 
        msg_area.setRows(5);
        msg_area.setBackground(Color.pink);
        jScrollPane1.setViewportView(msg_area);

        
        msg_send.setText("send");
        msg_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });
        //fampisehoana
        
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        jLabel1.setText(" CHAT_Serveur");
        jLabel1.setForeground(Color.blue);
        p.add(jLabel1);
        JPanel p1=new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        jScrollPane1.setPreferredSize(new Dimension(400,150));
        jScrollPane1.setBackground(Color.pink);
        p1.add(jScrollPane1);
        JPanel p2=new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
        msg_txt.setPreferredSize(new Dimension(330,70));
        msg_send.setText("send");
        msg_send.setPreferredSize(new Dimension(60,70));
        p2.add(msg_txt);
        p2.add(msg_send);
        JPanel pan=new JPanel();
        pan.setSize(500,500);
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        pan.add(p);
        pan.add(p1);
        pan.add(p2);
        
        setContentPane(pan);


        pack();

    }
    
    private void msg_sendActionPerformed(ActionEvent evt) {
        try {
            
            String message = "";
            message = msg_txt.getText().trim();
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {}
        
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Serveur().setVisible(true);
            
        });
         String message = "";
        try {
             serverSocket = new ServerSocket(1234); 
             socket = serverSocket.accept();
             
             dataInputStream = new DataInputStream(socket.getInputStream());
             dataOutputStream = new DataOutputStream(socket.getOutputStream());

             while (!message.equals("exit")) {
                message = dataInputStream.readUTF();
                msg_area.setText(msg_area.getText().trim()+ "\n" + message);

             }
        } catch (IOException e) {
        }
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_txt;
}
