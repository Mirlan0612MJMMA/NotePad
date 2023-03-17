import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viewer {
    private JTextArea textArea;
    private JFrame frame;
    private JFileChooser fileChooser;
    private Font fontLabelFontSample;
    private JTextField textFieldFont;
    private JTextField textFieldFontStyle;
    private JTextField textFieldFontSize;
    private JDialog diolog;
    private int x;
    private int y;
    private JDialog diologAbout;
    private String text;

    private JLabel statusMore;

    private JCheckBoxMenuItem wordSpaceBoxMenuItem;
    private Object JRootPane;
    public Viewer() {
        Controller controller = new Controller(this);
        initialization(controller);

    }

    private void initialization(Controller controller) {

        // front-end for JFrame
//        String name = "In Xanadu did Kubla Khan" +
//                "A stately pleasure-dome decree: " +
//                "Where Alph, the sacred river, ran \n" +
//                "Through caverns measureless to man " +
//                "Down to a sunless sea. \n";

        textArea = new JTextArea();
        textArea.setForeground(new Color(0,0 , 0));
        Font font = new Font("tahoma", Font.PLAIN, 18);
        textArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuBar menuBar = getMyJMenuBar(controller);
        frame = new JFrame("NotePadAUCA");
        frame.setSize(1020, 520);
        frame.setLocation(250, 100);
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JLabel getStatusMore() {
        return statusMore;
    }

    private JMenuBar getMyJMenuBar(Controller controller) {

        // Front-end for File Menu
        JMenuItem newMenuItem = new JMenuItem("New", new ImageIcon("images/new.gif"));
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newMenuItem.setMnemonic('N');
        newMenuItem.addActionListener(controller);
        newMenuItem.setActionCommand("Set_New_Document");

        JMenuItem openMenuItem = new JMenuItem("Open", new ImageIcon("images/open.gif"));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openMenuItem.setMnemonic('O');
        openMenuItem.addActionListener(controller);
        openMenuItem.setActionCommand("Set_Open_Document");

        JMenuItem saveMenuItem = new JMenuItem("Save", new ImageIcon("images/save.gif"));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveMenuItem.setMnemonic('S');
        saveMenuItem.addActionListener(controller);
        saveMenuItem.setActionCommand("Set_SaveDocument");

        JMenuItem saveAsMenuItem = new JMenuItem("Save As ...", new ImageIcon("images/save_as.gif"));
        saveAsMenuItem.setMnemonic('A');
        saveAsMenuItem.addActionListener(controller);
        saveAsMenuItem.setActionCommand("Set_Save_As");

        JMenuItem printMenuItem = new JMenuItem("Print ...", new ImageIcon("images/print.gif"));
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printMenuItem.setMnemonic('P');
        printMenuItem.addActionListener(controller);
        printMenuItem.setActionCommand("Set_Print");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(controller);
        exitMenuItem.setActionCommand("Set_Exit");

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(printMenuItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitMenuItem);


        // Front-end for Edit Menu
        JMenuItem cutMenuItem = new JMenuItem("Cut", new ImageIcon("images/cut.gif"));
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutMenuItem.setMnemonic('C');
        cutMenuItem.addActionListener(controller);
        cutMenuItem.setActionCommand("Set_Cut");

        JMenuItem copyMenuItem = new JMenuItem("Copy", new ImageIcon("images/copy.gif"));
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyMenuItem.setMnemonic('o');
        copyMenuItem.addActionListener(controller);
        copyMenuItem.setActionCommand("Set_Copy");

        JMenuItem pasteMenuItem = new JMenuItem("Paste", new ImageIcon("images/past.gif"));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteMenuItem.setMnemonic('P');
        pasteMenuItem.addActionListener(controller);
        pasteMenuItem.setActionCommand("Set_Paste");

        JMenuItem clearMenuItem = new JMenuItem("Clear", new ImageIcon("images/delit.gif"));
        clearMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        clearMenuItem.setMnemonic('l');
        clearMenuItem.addActionListener(controller);
        clearMenuItem.setActionCommand("Set_Clear");

        JMenuItem findMenuItem = new JMenuItem("Find", new ImageIcon("images/find.gif"));
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findMenuItem.setMnemonic('F');
        findMenuItem.addActionListener(controller);
        findMenuItem.setActionCommand("set_Find");

        JMenuItem findMoreMenuItem = new JMenuItem("Find more ...");
        findMoreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        findMoreMenuItem.setMnemonic('M');
        findMoreMenuItem.addActionListener(controller);
        findMoreMenuItem.setActionCommand("set_Find_More");

        JMenuItem goMenuItem = new JMenuItem("Go", new ImageIcon("images/go.gif"));
        goMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        goMenuItem.setMnemonic('G');
        goMenuItem.addActionListener(controller);
        goMenuItem.setActionCommand("Set_Go");

        JMenuItem markerAllMenuItem = new JMenuItem("MarkerAll", new ImageIcon("images/marker.gif"));
        markerAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        markerAllMenuItem.setMnemonic('M');
        markerAllMenuItem.addActionListener(controller);
        markerAllMenuItem.setActionCommand("Set_MarkerAll");

        JMenuItem timeAndDateMenuItem = new JMenuItem("Time and date", new ImageIcon("images/time.gif"));
        timeAndDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        timeAndDateMenuItem.setMnemonic('d');
        timeAndDateMenuItem.addActionListener(controller);
        timeAndDateMenuItem.setActionCommand("Set_TimeAndDate");

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(clearMenuItem);
        editMenu.add(new JSeparator());
        editMenu.add(findMenuItem);
        editMenu.add(findMoreMenuItem);
        editMenu.add(goMenuItem);
        editMenu.add(new JSeparator());
        editMenu.add(markerAllMenuItem);
        editMenu.add(timeAndDateMenuItem);


        // Front-end for Format Menu
        wordSpaceBoxMenuItem = new JCheckBoxMenuItem("Word space", new ImageIcon("images/wordSpace.gif"));
        wordSpaceBoxMenuItem.setMnemonic('W');
        wordSpaceBoxMenuItem.addActionListener(controller);
        wordSpaceBoxMenuItem.setActionCommand("Set_Word_Space");

        JMenuItem fontMenuItem = new JMenuItem("Font", new ImageIcon("images/font.gif"));
        fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        fontMenuItem.setMnemonic('o');
        fontMenuItem.addActionListener(controller);
        fontMenuItem.setActionCommand("Set_Font_New_Diolog");

        JMenuItem fontColorMenuItem = new JMenuItem("Font Color", new ImageIcon("images/color.gif"));
        fontColorMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        fontColorMenuItem.setMnemonic('C');
        fontColorMenuItem.addActionListener(controller);
        fontColorMenuItem.setActionCommand("Set_Font_Color");

        JMenu formatMenu = new JMenu("Format");
        formatMenu.setMnemonic('F');
        formatMenu.add(wordSpaceBoxMenuItem);
        formatMenu.add(fontMenuItem);
        formatMenu.add(fontColorMenuItem);


        // Front-end for View Menu
        JMenuItem statusSpaceMenuItem = new JMenuItem("Status space", new ImageIcon("images/options.gif"));

        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        viewMenu.add(statusSpaceMenuItem);


        // front-end for Help Menu
        JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
        viewHelpMenuItem.setMnemonic('H');
        viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        viewHelpMenuItem.setEnabled(false);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(controller);
        aboutMenuItem.setActionCommand("Set_About");

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(aboutMenuItem);


        // front-end for JMenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        return menuBar;

    }

    public void update(String text) {
        textArea.setText(text);
    }

    public File getFile(String action) {
        File selectedFile = null;

        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = -7;
        if (action.equals("Open")) {
            returnVal = fileChooser.showOpenDialog(frame);

        } else if (action.equals("Save")) {
            returnVal = fileChooser.showSaveDialog(frame);
        } else if (action.equals("Save As ...")) {           ////////////
            returnVal = fileChooser.showSaveDialog(frame);  ////////////
        }                                                   ////////////
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        return selectedFile;
    }

    public String showDiologNewDocument() {
        int n = -1;
        String text = textArea.getText();

        if (!text.equals("")) {

            Object[] options = {"Save", "Don't Save", "Cancel"};
            n = JOptionPane.showOptionDialog(frame, "Do you want to save changes"
                            + "to Untitled?",
                    "NotePadAUCA",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            System.out.println("n = " + n);
            if (n == 0) {
                return text;
            }
        }
        return null;

    }

    public String getTextFromTextArea() {
        return textArea.getText();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void showDiologForFont(Controller controller) {
        int width = 455;
        int height = 420;
        int x = frame.getLocation().x;
        int y = frame.getLocation().y;

        //Front-end for display Sample

        fontLabelFontSample = new Font("Algerian", Font.PLAIN, 32);
        TitledBorder title = BorderFactory.createTitledBorder("Sample");
        JLabel labelFontSample = new JLabel("AaBbYyZz");
        labelFontSample.setBounds(170, 155, 260, 75);
        labelFontSample.setHorizontalAlignment(JLabel.CENTER);
        labelFontSample.setBorder(title);
        labelFontSample.setFont(fontLabelFontSample);

        // Front-end for Font

        JLabel jLabelFont = new JLabel("Font:");
        jLabelFont.setBounds(10, 10, 145, 15);
//

        textFieldFont = new JTextField();
        textFieldFont.setBounds(10, 26, 145, 25);

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = graphicsEnvironment.getAvailableFontFamilyNames();

        textFieldFont.setText(fontNames[0]);
        textFieldFont.setFont(new Font("Arial", Font.BOLD, 14));

        Font[] allFonts = graphicsEnvironment.getAllFonts();
        JList listFont = new JList(fontNames);
        listFont.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting() == false) {
                    if (listFont.getSelectedIndex() != -1) {
                        String selectedValue = (String) listFont.getSelectedValue();
                        textFieldFont.setText(selectedValue);
                        Font font = new Font(selectedValue, Font.PLAIN, 32);
                        labelFontSample.setFont(font);
                    }
                }
            }
        });
        JScrollPane scrollList = new JScrollPane(listFont);
        scrollList.setBounds(10, 50, 145, 90);

        //Front-end for Font Style


        JLabel jLabelFontStyle = new JLabel("Font Style:");
        jLabelFontStyle.setBounds(172, 10, 180, 15);

        textFieldFontStyle = new JTextField();
        textFieldFontStyle.setBounds(172, 26, 180, 25);
        textFieldFontStyle.setFont(new Font("Arial", Font.BOLD, 14));
        String[] choicesStyle = {"Regular", "Italic", "Bold", "Bold Italic"};
        textFieldFontStyle.setText(choicesStyle[0]);
        JList listFontStyle = new JList(choicesStyle);
        listFontStyle.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting() == false) {
                    if (listFontStyle.getSelectedIndex() != -1) {
                        String selectedValue = (String) listFontStyle.getSelectedValue();
                        textFieldFontStyle.setText(selectedValue);
                        Font font = new Font(selectedValue, Font.PLAIN, 32);
                        labelFontSample.setFont(font);
                    }
                }
            }
        });
        JScrollPane scrollListStyle = new JScrollPane(listFontStyle);
        scrollListStyle.setBounds(172, 50, 180, 90);


        //front-end Font Size

        JLabel jLabelSize = new JLabel("Size:");
        jLabelSize.setBounds(370, 10, 60, 15);

        textFieldFontSize = new JTextField();
        textFieldFontSize.setBounds(370, 26, 60, 25);

        String[] choicesSize = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
        textFieldFontSize.setText(choicesSize[0]);
        textFieldFontSize.setFont(new Font("Arial", Font.BOLD, 14));
        JList listFontSize = new JList(choicesSize);
        listFontSize.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting() == false) {
                    if (listFontSize.getSelectedIndex() != -1) {
                        String selectedValue = (String) listFontSize.getSelectedValue();
                        textFieldFontSize.setText(selectedValue);
                        Font font = new Font("Arial", Font.PLAIN, Integer.parseInt(selectedValue));
                        labelFontSample.setFont(font);
                    }
                }
            }
        });
        JScrollPane scrollListSize = new JScrollPane(listFontSize);
        scrollListSize.setBounds(370, 50, 60, 90);

        // Front-end for JButton Ok and Cancel
        JButton buttonOk = new JButton("Ok");
        buttonOk.setBounds(270, 345, 73, 23);
        buttonOk.addActionListener(controller);
        buttonOk.setActionCommand("Set_Ok");


        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(350, 345, 73, 23);
        buttonCancel.addActionListener(controller);
        buttonCancel.setActionCommand("Set_Cancel");


        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(true);
        backgroundPanel.setBounds(2, 2, width - 20, height - 42);

        backgroundPanel.add(jLabelFont);
        backgroundPanel.add(textFieldFont);
        backgroundPanel.add(scrollList);
        backgroundPanel.add(jLabelFontStyle);
        backgroundPanel.add(textFieldFontStyle);
        backgroundPanel.add(scrollListStyle);
        backgroundPanel.add(jLabelSize);
        backgroundPanel.add(textFieldFontSize);
        backgroundPanel.add(scrollListSize);
        backgroundPanel.add(labelFontSample);
        backgroundPanel.add(buttonOk);
        backgroundPanel.add(buttonCancel);

        diolog = new JDialog(frame, "Font", true);
        diolog.setSize(width, height);   //  455, 425)
        diolog.setLocation(x + 280, y + 70);
        diolog.setLayout(null);
        diolog.add(backgroundPanel);
        diolog.setVisible(true);
    }

    public String getFontName() {
        return textFieldFont.getText();
    }

    public String getFontStyle() {
        return textFieldFontStyle.getText();
    }

    public int getFontSize() {
        return Integer.parseInt(textFieldFontSize.getText());
    }

    public void setNewFontForTextArea(String FontName, String FontStyle, int FontSize) {
        Font newFont = new Font(FontName, Font.BOLD, FontSize);
        textArea.setFont(newFont);

    }

    public void closeFontDiologCancel() {
        diolog.setVisible(false);
    }

    public void selectedFontDiologOk() {
        String FontName = getFontName();
        String FontStyle = getFontStyle();
        int FontSize = getFontSize();
        setNewFontForTextArea(FontName, FontStyle, FontSize);
        diolog.setVisible(false);

    }

    public void aboutDiologProgram(Controller controller) {
        x = frame.getLocation().x;
        y = frame.getLocation().y;

        JLabel iconLabel = new JLabel(new ImageIcon("images/NotePad.png"));
        iconLabel.setBounds(40, 50, 80, 80);

        JLabel textLabel = new JLabel("<html>Welcome to NotePad<br>NotePad is a simple " +
                "text  editor for Microsoft Windows and a basic text-editing program which" +
                " enables computer users to creat documents<br>All rights reserved@2021</html>");
        textLabel.setBounds(50, 50, 400, 300);

        JButton buttonAboutDiolog = new JButton("Ok");
        buttonAboutDiolog.setBounds(350, 345, 73, 23);
        buttonAboutDiolog.addActionListener(controller);
        buttonAboutDiolog.setActionCommand("Set_About_Ok");

        diologAbout = new JDialog(frame, "About the program", true);
        diologAbout.setSize(455, 425);   //  455, 425)
        diologAbout.setLocation(x + 280, y + 70);
        diologAbout.setLayout(null);
        diologAbout.add(iconLabel);
        diologAbout.add(textLabel);
        diologAbout.add(buttonAboutDiolog);
        diologAbout.setVisible(true);
    }

    public void CutAction(Controller controller) {
        text = textArea.getSelectedText();
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
    }

    public void Cupy(Controller controller) {
        text = textArea.getSelectedText();
    }

    public void paste(Controller controller) {
        textArea.insert(text, textArea.getCaretPosition());
    }

    public void clear() {
        textArea.setText("");
    }

    public void wordSpace() {
        if (wordSpaceBoxMenuItem.isSelected()) {
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
        } else {
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);
        }
    }

    public void fontColor() {
        Color color = JColorChooser.showDialog((Component) JRootPane, "Choose Font Color", Color.blue);
        textArea.setForeground(color);
    }

    public void markerAll() {
        textArea.selectAll();
    }

    public void goTo() {
        int lineNumber = 0;
        try {
            lineNumber = textArea.getLineOfOffset(textArea.getCaretPosition()) + 1;
            String tempStr = JOptionPane.showInputDialog(frame, "Enter Line Number:", "" + lineNumber);
            if (tempStr == null) {
                return;
            }
            lineNumber = Integer.parseInt(tempStr);
            textArea.setCaretPosition(textArea.getLineStartOffset(lineNumber - 1));
        } catch (Exception e) {
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void closeAboutDiologProgram() {
        diologAbout.setVisible(false);
    }
}
