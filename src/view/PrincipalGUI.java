package view;

import util.FuncaoCopia;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Arsênio
 * @since 26/05/2015
 * @version 1.0
 */
public class PrincipalGUI extends javax.swing.JFrame {

    public PrincipalGUI() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        System.out.println("antes");
        initComponents();
        System.out.println("depois");
        this.setLocationRelativeTo(null);
    }

    private void abrirSeletorLista() {
        int acao;
        String caminho = System.getProperty("user.home").concat("\\Music\\Listas de Reprodução");

        seletor.setCurrentDirectory(new File(caminho));
        seletor.setFileSelectionMode(JFileChooser.FILES_ONLY);
        acao = seletor.showOpenDialog(this);

        if (acao == JFileChooser.APPROVE_OPTION) {
            caminho = seletor.getSelectedFile().getPath();
            if (caminho.endsWith(".wpl")) {
                txLista.setText(caminho);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione apenas arquivos de lista de reprodução (.wpl)");
                txLista.setText("");
            }
        }
    }

    private void abrirSeletorDestino() {
        int acao;
        String caminho = System.getProperty("user.home").concat("\\Desktop");

        seletor.setCurrentDirectory(new File(caminho));
        seletor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        acao = seletor.showOpenDialog(this);

        if (acao == JFileChooser.APPROVE_OPTION) {
            caminho = seletor.getSelectedFile().getPath();
            txDestino.setText(caminho);
        } else {
            txDestino.setText("");
        }
    }

    private void iniciarCopia() {
        if (txLista.getText().equals("") || txLista.getText() == null) {
            JOptionPane.showMessageDialog(null, "Informe o caminho da lista de reprodução.");
            throw new IllegalArgumentException("Lista de reprodução não informada.");
        }
        if (txDestino.getText().equals("") || txDestino.getText() == null) {
            JOptionPane.showMessageDialog(null, "Informe o caminho da pasta destino.");
            throw new IllegalArgumentException("Pasta destino não informada.");
        }

        new Thread() {
            @Override
            public void run() {
                new FuncaoCopia().iniciarCopia(
                        new File(txLista.getText()), txDestino.getText(), barraProgresso, lbStatus);
            }
        }.start();
        txLista.setEnabled(false);
        txDestino.setEnabled(false);
        btProcuraLista.setEnabled(false);
        btProcuraDestino.setEnabled(false);
        txDestino.setEnabled(false);
        btCopiar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seletor = new javax.swing.JFileChooser();
        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbLista = new javax.swing.JLabel();
        txLista = new javax.swing.JTextField();
        lbDestino = new javax.swing.JLabel();
        txDestino = new javax.swing.JTextField();
        btProcuraLista = new javax.swing.JButton();
        btProcuraDestino = new javax.swing.JButton();
        btCopiar = new javax.swing.JButton();
        barraProgresso = new javax.swing.JProgressBar();
        sp1 = new javax.swing.JSeparator();
        lbStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CopyPlaylist by Gabriel Arsênio");
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Copiar Lista de Reprodução");

        lbLista.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbLista.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbLista.setText("Lista de Reprodução:");

        txLista.setNextFocusableComponent(btProcuraLista);

        lbDestino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDestino.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbDestino.setText("Pasta Destino:");

        txDestino.setNextFocusableComponent(btProcuraDestino);

        btProcuraLista.setText("Procurar...");
        btProcuraLista.setNextFocusableComponent(txDestino);
        btProcuraLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcuraListaActionPerformed(evt);
            }
        });

        btProcuraDestino.setText("Procurar...");
        btProcuraDestino.setNextFocusableComponent(btCopiar);
        btProcuraDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcuraDestinoActionPerformed(evt);
            }
        });

        btCopiar.setText("Copiar");
        btCopiar.setNextFocusableComponent(txLista);
        btCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCopiarActionPerformed(evt);
            }
        });

        barraProgresso.setName(""); // NOI18N
        barraProgresso.setStringPainted(true);

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addComponent(lbLista, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btProcuraLista))
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addComponent(lbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btProcuraDestino))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoLayout.createSequentialGroup()
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCopiar))
                    .addComponent(sp1))
                .addContainerGap())
        );
        painelFundoLayout.setVerticalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addGap(18, 18, 18)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLista)
                    .addComponent(txLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btProcuraLista))
                .addGap(18, 18, 18)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDestino)
                    .addComponent(txDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btProcuraDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barraProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addComponent(btCopiar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btProcuraListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcuraListaActionPerformed
        abrirSeletorLista();
    }//GEN-LAST:event_btProcuraListaActionPerformed

    private void btProcuraDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcuraDestinoActionPerformed
        abrirSeletorDestino();
    }//GEN-LAST:event_btProcuraDestinoActionPerformed

    private void btCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCopiarActionPerformed
        iniciarCopia();
    }//GEN-LAST:event_btCopiarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JButton btCopiar;
    private javax.swing.JButton btProcuraDestino;
    private javax.swing.JButton btProcuraLista;
    private javax.swing.JLabel lbDestino;
    private javax.swing.JLabel lbLista;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JFileChooser seletor;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txDestino;
    private javax.swing.JTextField txLista;
    // End of variables declaration//GEN-END:variables
}
