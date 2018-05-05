package MusicPlayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TriplePlayMusicPlayer extends javax.swing.JFrame {

    static LinkedList<SongMetadata> songsList;
    static String[] myFileNames;

    public TriplePlayMusicPlayer() {
        initComponents();
        FileHandler mySongsFile = new FileHandler("songs_list.dat");

        if (mySongsFile.getIsNull()) {
            songsList = mySongsFile.readFile();
        }

        if (songsList == null) {
            songsList = new LinkedList<>();
        } else {
            myFileNames = new String[songsList.size()];
            for (int i = 0; i < songsList.size(); i++) {
                myFileNames[i] = songsList.get(i).getFileName();
                jList2.setListData(myFileNames);
            }
        }

        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones...");
        JMenuItem addItem = new JMenuItem("Añadir archivo a la Lista de Reproducción");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                int isSelected = fc.showOpenDialog(null);

                if (isSelected == JFileChooser.APPROVE_OPTION) {
                    File newSong = fc.getSelectedFile();
                    FileHandler.copyFile(newSong);
                    SongMetadata addedSong = new SongMetadata();

                    try {
                        FieldGetter songInfo = new FieldGetter(newSong);
                        addedSong.setSongTitle(songInfo.getTitle());
                        addedSong.setArtistName(songInfo.getArtist());
                        addedSong.setAlbumName(songInfo.getAlbum());

                    } catch (CannotReadFile ex) {
                        Logger.getLogger(TriplePlayMusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    addedSong.setFileName(newSong.getName());
                    songsList.add(addedSong);

                    myFileNames = new String[songsList.size()];

                    for (int i = 0; i < songsList.size(); i++) {
                        myFileNames[i] = songsList.get(i).getFileName();
                    }

                    jList2.setListData(myFileNames);

                    System.out.println(newSong.getAbsolutePath());
                    System.out.println(newSong.getPath());
                    System.out.println(newSong.getName());
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Cerrar");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mySongsFile.writeFile(songsList);
                System.exit(0);
            }
        });
        menu.add(addItem);
        menu.add(exitItem);
        menuBar.add(menu);

        JFXPanel fxPanel = new JFXPanel();

        SoundControlPlayerHandler myControlPlayer = new SoundControlPlayerHandler(jToggleButton1);
        jList2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    JList source = (JList) lse.getSource();
                    String selected = source.getSelectedValue().toString();
                    System.out.println(selected);
                    myControlPlayer.setMediaControls(selected);
                    myControlPlayer.getMediaPlayer().play();
                    for (int i = 0; i < songsList.size(); i++) {
                        if (selected.equals(songsList.get(i).getFileName())) {
                            jLabel5.setText(songsList.get(i).getSongTitle());
                            jLabel11.setText(songsList.get(i).getArtistName());
                            jLabel12.setText(songsList.get(i).getAlbumName());
                        }
                    }

                }
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        jToggleButton1 = myControlPlayer.getPlayButton();

        jToggleButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (myControlPlayer.getMediaPlayer().isMute()) {
                    myControlPlayer.getMediaPlayer().setMute(false);
                } else {
                    myControlPlayer.getMediaPlayer().setMute(true);
                }

            }
        });

        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(BorderLayout.CENTER, jPanel1);
        add(BorderLayout.SOUTH, jPanel3);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TriplePlay Music Player");
        setBackground(new java.awt.Color(7, 62, 85));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(7, 62, 85));

        jLabel1.setFont(new java.awt.Font("Lato Light", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 190, 223));
        jLabel1.setText("&& @BalbyReny");

        jLabel2.setFont(new java.awt.Font("Lato Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(31, 190, 223));
        jLabel2.setText("by @ AdrianGuevara");

        jLabel3.setFont(new java.awt.Font("Lato Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(31, 190, 223));
        jLabel3.setText("+ @LuisBar05");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/header.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lato Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(31, 190, 223));
        jLabel4.setText("//Find us at GitHub");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(7, 62, 85));

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play.png"))); // NOI18N
        jToggleButton1.setPreferredSize(new java.awt.Dimension(80, 80));
        jToggleButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pause.png"))); // NOI18N
        jToggleButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play.png"))); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(58, 58));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prev.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(58, 58));

        jToggleButton2.setFont(new java.awt.Font("Lato Light", 0, 10)); // NOI18N
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unmute.png"))); // NOI18N
        jToggleButton2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(15, 15));
        jToggleButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unmute.png"))); // NOI18N
        jToggleButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/muted.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(13, 13, 13))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        jPanel4.setBackground(new java.awt.Color(228, 240, 243));

        jLabel6.setFont(new java.awt.Font("Lato", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 62, 85));
        jLabel6.setText("Lista de Reproducción");

        jLabel7.setFont(new java.awt.Font("Lato", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(7, 62, 85));
        jLabel7.setText("Información de la Pista");

        jList2.setBackground(new java.awt.Color(228, 240, 243));
        jList2.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        jList2.setForeground(new java.awt.Color(7, 62, 85));
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setToolTipText("");
        jList2.setSelectionBackground(new java.awt.Color(7, 62, 85));
        jList2.setSelectionForeground(new java.awt.Color(3, 182, 216));
        jList2.setVisibleRowCount(10);
        jScrollPane2.setViewportView(jList2);

        jPanel5.setBackground(new java.awt.Color(228, 240, 243));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 86, 110)));
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Lato", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(7, 62, 85));
        jLabel8.setText("Título:");

        jLabel9.setFont(new java.awt.Font("Lato", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(7, 62, 85));
        jLabel9.setText("Artista:");

        jLabel10.setFont(new java.awt.Font("Lato", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(7, 62, 85));
        jLabel10.setText("Álbum:");

        jLabel5.setText("AwesomeTitle");

        jLabel11.setText("CoolArtist");

        jLabel12.setText("GOATAlbum");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(96, 96, 96))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        getAccessibleContext().setAccessibleDescription("[POO] TriplePlay Music Player\nProyecto de Segundo Parcial de Programación Orientada a Objetos.\nUn reproductor de archivos de audio simple utilizando diversos componentes Swing.");
        getAccessibleContext().setAccessibleParent(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TriplePlayMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TriplePlayMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TriplePlayMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TriplePlayMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TriplePlayMusicPlayer().setVisible(true);
            }
        });
    }

    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JMenuBar menuBar;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
