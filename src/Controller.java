import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller implements ActionListener {


    private Viewer viewer;
    private FindDialog findDialog = null;

    public Controller(Viewer viewer) {
        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "Set_New_Document":
                String text = viewer.showDiologNewDocument();
                if (text != null) {
                    File file = viewer.getFile("Save");
                    if (file != null) {
                        saveDocument(file, text);
                    } else {
                        viewer.update("Error");
                    }
                }
                break;
            case "Set_Open_Document":
                File file = viewer.getFile("Open");
                if (file != null) {
                    String textForOpen = openFile(file);
                    viewer.update(textForOpen);
                } else {
                    viewer.update("");
                }
                break;
            case "Set_SaveDocument":
                String textForSave = viewer.getTextFromTextArea();
                if (textForSave != null) {
                    File fileForSave = viewer.getFile("Save");
                    saveDocument(fileForSave, textForSave);
                }
                break;
            case "Set_Save_As":
                String textForSaveAs = viewer.getTextFromTextArea();
                if (textForSaveAs != null) {
                    File fileForSave = viewer.getFile("Save As ...");
                    saveDocument(fileForSave, textForSaveAs);
                }
                break;
            case "Set_Print":
                String textOne = viewer.getTextFromTextArea();
                System.out.println("set_print_12: \n" + textOne);
                new PaginationExample(textOne).printMyDocument();
                break;
            case "Set_Cut":
                viewer.CutAction(this);
                break;
            case "Set_Copy":
                viewer.Cupy(this);
                break;
            case "Set_Paste":
                viewer.paste(this);
                break;
            case "Set_Clear":
                viewer.clear();
                break;
            case "Set_MarkerAll":
                viewer.markerAll();
                break;
            case "set_Find":
                findDialog = new FindDialog(viewer.getTextArea());
                findDialog.showDialog(Controller.this.viewer.getFrame(),true);
                break;
            case "set_Find_More":
                if (findDialog == null)
                viewer.getStatusMore().setText("Nothing to search for, use Find option of Edit Menu first !!!");
                else
                    findDialog.findNextWithSelection();
                break;
            case "Set_Go":
                viewer.goTo();
                break;
            case "Set_TimeAndDate":
                timeAndDate();
                break;
            case "Set_Word_Space":
                viewer.wordSpace();
                break;
            case "Set_Font_New_Diolog":
                viewer.showDiologForFont(this);
                break;
            case "Set_Font_Color":
                viewer.fontColor();
                break;
            case "Set_Cancel":
                viewer.closeFontDiologCancel();
                break;
            case "Set_Ok":
                viewer.selectedFontDiologOk();
                break;
            case "Set_About":
                viewer.aboutDiologProgram(this);
                break;
            case "Set_About_Ok":
                viewer.closeAboutDiologProgram();
                break;
            case "Set_Exit":
                System.out.println("Good bye");
                System.exit(1);
                break;
            default:
        }
    }

    private void saveDocument(File fileName, String text) {
        if (fileName == null) return;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter outputStream = new PrintWriter(fileWriter);
            outputStream.println(text);
            outputStream.close();
        } catch (Exception e) {
            System.out.println("file19: " + fileName + "\n text: \n" + text);
            System.out.println(e);
        }
        //return text;
    }

    private String openFile(File fileName) {
        String text = "";
        try {
            FileReader in = new FileReader(fileName);
            BufferedReader inputStream = new BufferedReader(in);
            String l;
            while ((l = inputStream.readLine()) != null) {
                text = text + l + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return text;
    }

    public void timeAndDate() {
        LocalDateTime myDateObject = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = myDateObject.format(myFormatObj);
        viewer.getTextArea().setText(formattedDate);
    }


}
