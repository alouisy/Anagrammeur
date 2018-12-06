/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jeuxtrouverlemot;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jeuxtrouverlemot.bean.Anagram;


/**
 *
 * @author darutydegrandprevincent
 */
public class MainFrame extends JFrame implements ActionListener {
        private Anagram word;
       

        private JPanel mainPanel;
        private JLabel scrambledLabel = new JLabel("Mot a reconstituer : ");
        private JTextField scrambledWord = new JTextField();
        private JLabel guessLabel = new JLabel("Votre reponse : ");
        private JTextField guessedWord = new JTextField();
        private JLabel feedbackLabel = new JLabel();
        private JButton buttonsPanel = new JButton("");
        private JButton guessButton = new JButton("valider");
        private JButton nextTrial = new JButton("essayer autre");
        private JButton showResp = new JButton("reponse");
        private JMenuBar mainMenu = new JMenuBar();
        private JMenu fileMenu= new JMenu("File");
        private JMenuItem aboutMenuItem = new JMenuItem("About");
        private JMenuItem exitMenuItem = new JMenuItem("Exit");
        private JMenu lookAndFeel = new JMenu("Look and Feel");
        private JMenuItem chooseSkin = new JMenuItem("Choose Skin");
        private GridBagConstraints gridBagConstraints;
        private static MainFrame actual;

        public MainFrame(){
        
        actual = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Trouvez le mot");
        Container c = getContentPane();
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int midW = d.width/2;
        int midH = d.height/2;
        int frameW = 400;
        int frameH = 250;
        setBounds(midW - (frameW/2), midH - (frameH/2), frameW, frameH);

        word = new Anagram();

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 12, 12)));
        mainPanel.setMinimumSize(new java.awt.Dimension(297, 200));
        scrambledLabel.setText("Mot a trouver:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 6);
        mainPanel.add(scrambledLabel, gridBagConstraints);

        scrambledWord.setColumns(20);
        scrambledWord.setEditable(false);

        word.init();
        String mixedWord = word.mixedRandomWord();
        
        scrambledWord.setText(mixedWord);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        mainPanel.add(scrambledWord, gridBagConstraints);

        guessLabel.setDisplayedMnemonic('Y');
        guessLabel.setLabelFor(guessedWord);
        guessLabel.setText("Votre reponse:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 6);
        mainPanel.add(guessLabel, gridBagConstraints);

        guessedWord.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        mainPanel.add(guessedWord, gridBagConstraints);

        feedbackLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1/2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        mainPanel.add(feedbackLabel, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        guessButton.setMnemonic('G');
        guessButton.setText("Valider");
        guessButton.setToolTipText("donnez votre reponse");
        guessButton.addActionListener(this);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        buttonsPanel.add(guessButton, gridBagConstraints);

        showResp.addActionListener(this);
        showResp.setMnemonic('R');
        showResp.setToolTipText("afficher la reponse");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 6);
        buttonsPanel.add(showResp, gridBagConstraints);

        nextTrial.setMnemonic('N');
        nextTrial.setText("Nouveau mot");
        nextTrial.setToolTipText("Changer de mot");
        nextTrial.addActionListener(this);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(nextTrial, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(buttonsPanel, gridBagConstraints);

        c.add(mainPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.setToolTipText("About");


        fileMenu.add(aboutMenuItem);

        exitMenuItem.setMnemonic('E');
        exitMenuItem.setText("Exit");
        exitMenuItem.setToolTipText("Quit Team, Quit!");
        exitMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });

        lookAndFeel.setMnemonic('L');
        chooseSkin.setMnemonic('S');
        chooseSkin.setToolTipText("Change the appearance");
        chooseSkin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = showDialog();
                setLookAndFeel(str);
            }
        });
        fileMenu.add(exitMenuItem);
        lookAndFeel.add(chooseSkin);
        mainMenu.add(fileMenu);
        mainMenu.add(lookAndFeel);

        setJMenuBar(mainMenu);
        SwingUtilities.updateComponentTreeUI(this);
        
  }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == guessButton){
            String s = guessedWord.getText();
            if (word.isEquals(s)){
                feedbackLabel.setText("Gagne !! Essayez un autre mot");
            }
            if (!word.isEquals(s)){
                feedbackLabel.setText("Perdu !! Essayez encore");
                guessedWord.setText("");
            }
        }

        if (source == nextTrial){
            feedbackLabel.setText("");
            word = new Anagram();
            word.init();
            scrambledWord.setText(word.mixedRandomWord());
            guessedWord.setText("");
        }

        if (source == showResp){
            feedbackLabel.setText("L'erreur est humaine !!");
            String res = Anagram.getTheWord();
            guessedWord.setText(res);
        }
    }

    public String showDialog() {
        String tabLF[] = new String [UIManager.getInstalledLookAndFeels().length + 1];
        UIManager.LookAndFeelInfo tab[] = UIManager.getInstalledLookAndFeels();
        for (int i = 0 ; i < tabLF.length - 1 ; i++){
            tabLF[ i ] = tab[ i ].getName();
        }
        tabLF[tabLF.length - 1] = "Choose skin...";
        return (String) JOptionPane.showInputDialog(this, "Choisissez un look and feel", "Look And Feel", JOptionPane.QUESTION_MESSAGE, null, tabLF, tabLF[tabLF.length - 1]);
    }

    public void setLookAndFeel(String lookAndFeel) {
        String lf;
        if (lookAndFeel == "GTK+") {
            lf = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
        }
        else if (lookAndFeel == "CDE/Motif") {
            lf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        }
        else if (lookAndFeel == "Metal") {
            lf = UIManager.getSystemLookAndFeelClassName();
        }
        else {
               System.err.println("Unexpected value of LOOKANDFEEL specified: "
                                   + lookAndFeel);
               lf = UIManager.getSystemLookAndFeelClassName();
               }


        try {
                UIManager.setLookAndFeel(lf);
                SwingUtilities.updateComponentTreeUI(this);
                this.repaint();
            }

            catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel : " + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            }

            catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
                System.err.println("Using the default look and feel.");
            }

            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
     }

}
