import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

class FindDialog extends JPanel implements ActionListener {
    private JTextArea textAreaFind;
    private int lastIndex;
    private JLabel findWhatYou;

    private TextField findWhat;
    private JCheckBox matchCase;

    private JRadioButton up;
    private JRadioButton down;

    private JButton findNextButton;
    private JButton cancelButton;

    private JPanel direction;
    private JPanel replaceButtonPanel;

    private boolean ok;
    private JDialog dialog;

    ///////////////////////
    public FindDialog(JTextArea textAreaFind) {
        this.textAreaFind = textAreaFind;

        findWhat = new TextField(20);
        matchCase = new JCheckBox("Match case");

        up = new JRadioButton("Up");
        down = new JRadioButton("Down");

        down.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(up);
        buttonGroup.add(down);

        direction = new JPanel();
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Direction");
        direction.setBorder(titled);
        direction.setLayout(new GridLayout(1, 2));
        direction.add(up);
        direction.add(down);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));
        southPanel.add(matchCase);
        southPanel.add(direction);


        findNextButton = new JButton("Find Next");
        cancelButton = new JButton("Cancel");

        replaceButtonPanel = new JPanel();
        replaceButtonPanel.setLayout(new GridLayout(4, 1));
        replaceButtonPanel.add(findNextButton);
        replaceButtonPanel.add(cancelButton);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 2));
        textPanel.add(new JLabel("Find what "));
        textPanel.add(findWhat);
        textPanel.add(new JLabel(" "));
        textPanel.add(new JLabel(" "));
        setLayout(new BorderLayout());

        add(new JLabel("       "), BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(replaceButtonPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
        setSize(200, 200);

        findNextButton.addActionListener(this);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                dialog.setVisible(false);
            }
        });

        findWhat.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent te) {
                enableDisableButtons();
            }
        });
        findWhat.addTextListener(
                new TextListener() {

                    public void textValueChanged(TextEvent te) {
                        enableDisableButtons();
                    }
                });
    }

    //////////////////////////
    void enableDisableButtons() {
        if (findWhat.getText().length() == 0) {
            findNextButton.setEnabled(false);
        } else {
            findNextButton.setEnabled(true);
        }
    }

    ///////////////////////////////////
    public void actionPerformed(ActionEvent ev) {

        if (ev.getSource() == findNextButton)
            findNextWithSelection();
        System.out.println(123123);
    }

    /////////////////////////
    int findNext() {

        String strOne = textAreaFind.getText();
        String strTwo = findWhat.getText();

        lastIndex = textAreaFind.getCaretPosition();

        int selStart = textAreaFind.getSelectionStart();
        int selEnd = textAreaFind.getSelectionEnd();

        if (up.isSelected()) {
            if (selStart != selEnd)
                lastIndex = selEnd - strTwo.length() - 1;

            if (!matchCase.isSelected())
                lastIndex = strOne.toUpperCase().lastIndexOf(strTwo.toUpperCase(), lastIndex);
            else
                lastIndex = strOne.lastIndexOf(strTwo, lastIndex);
        } else {
            if (selStart != selEnd)
                lastIndex = selStart + 1;
            if (!matchCase.isSelected())
                lastIndex = strOne.toUpperCase().indexOf(strTwo.toUpperCase(), lastIndex);
            else
                lastIndex = strOne.indexOf(strTwo, lastIndex);
        }
        return lastIndex;
    }

    ///////////////////////////////////////////////
    public void findNextWithSelection() {
        int idx = findNext();
        if (idx != -1) {
            textAreaFind.setSelectionStart(idx);
            textAreaFind.setSelectionEnd(idx + findWhat.getText().length());
        } else
            JOptionPane.showMessageDialog(this,
                    "Cannot find" + " \"" + findWhat.getText() + "\"",
                    "Find", JOptionPane.INFORMATION_MESSAGE);
    }

    //////////////////////////////////////////////
    public boolean showDialog(Component parent, boolean isFind) {

        Frame owner = null;
        if (parent instanceof Frame)
            owner = (Frame) parent;
        else
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        if (dialog == null || dialog.getOwner() != owner) {
            dialog = new JDialog(owner, false);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(findNextButton);
        }

        if (findWhat.getText().length() == 0)
            findNextButton.setEnabled(false);
        else
            findNextButton.setEnabled(true);

        if (isFind) {
            dialog.setSize(460, 180);
            dialog.setTitle("Find");
        }
        dialog.setVisible(true);

//System.out.println(dialog.getWidth() + " " + dialog.getHeight());
        return ok;
    }
}
