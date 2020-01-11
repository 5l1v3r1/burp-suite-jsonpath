package burp.ui;

import burp.BurpExtender;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonFormatter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Component that provides the UI for JSONPath queries and displaying results. 
 * 
 * @author August Detlefsen
 */
public class JsonPathPanel extends javax.swing.JPanel {

    private final String json;
    private final DocumentContext context;

    /**
     * Creates new JsonPathPanel
     */
    public JsonPathPanel(String json) {
        this.json = json;
        context = JsonPath.parse(json);
        initComponents();
        
        //add a listener to the domains list
        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                String selected = resultList.getSelectedValue();
                if (selected == null) {
                    copyButton.setText("Copy All");
                } else {
                    copyButton.setText("Copy Selection");
                }
            }
        });
        
        customizeUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsonPathEntry = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        statusMessage = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList<>();
        copyButton = new javax.swing.JButton();

        jsonPathEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jsonPathEntryKeyPressed(evt);
            }
        });

        jButton1.setText("Go!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        statusMessage.setText("JSONPath");

        resultArea.setColumns(20);
        resultArea.setRows(5);
        jScrollPane1.setViewportView(resultArea);

        jTabbedPane1.addTab("JSON", jScrollPane1);

        jScrollPane2.setViewportView(resultList);

        copyButton.setText("Copy All");
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(copyButton)
                .addGap(0, 544, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 413, Short.MAX_VALUE)
                .addComponent(copyButton))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                    .addGap(31, 31, 31)))
        );

        jTabbedPane1.addTab("List", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(statusMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsonPathEntry)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsonPathEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(statusMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        evaluatePath();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jsonPathEntryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jsonPathEntryKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evaluatePath();
        }
    }//GEN-LAST:event_jsonPathEntryKeyPressed

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        StringBuilder clip = new StringBuilder();

        if (resultList.getSelectedIndex() > 0) {
            for (Object o : resultList.getSelectedValuesList()) {
                String value = o.toString();
                clip.append(value).append("\n");
            }
        } else {
            //select all 
            ListModel selected = resultList.getModel();
            for (int i = 0; i < selected.getSize(); i++) {
                String value = (String)selected.getElementAt(i);
                clip.append(value).append("\n");
            }
        }
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(new StringSelection(clip.toString()), null);

        setStatusMessage("Selection copied");
    }//GEN-LAST:event_copyButtonActionPerformed

    public void evaluatePath() {
        String jsonPathText = jsonPathEntry.getText();

        try {
            JsonPath path = JsonPath.compile(jsonPathText);
            BurpExtender.getInstance().getCallbacks().printOutput(path.toString());
        } catch (Exception e) {
            BurpExtender.getInstance().printStackTrace(e);

            String message = e.getMessage();
            if ("java.lang.NullPointerException".equals(message)) {
                message = "Invalid path";
            }
            setStatusMessage(message);
        }

        //eval the JSON path
        Object results = JsonPath.parse(json).read(jsonPathText);

        //show the pretty print results (as JSON)
        resultArea.setText(JsonFormatter.prettyPrint(results.toString()));

        //show the results as a list
        if (results instanceof List) {
            DefaultListModel listModel = new DefaultListModel();
            for (Object result : (List) results) {
                BurpExtender.getInstance().getCallbacks().printOutput(noNulls(result));
                listModel.addElement(noNulls(result));
            }
            resultList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            resultList.setModel(listModel);
        }

    }

    public void setJsonPathEntry(String path) {
        jsonPathEntry.setText(path);
    }

    public void setStatusMessage(String message) {
        //update the UI
        statusMessage.setText(message);

        //hide the status message after a delay
        Timer timer = new Timer();
        timer.schedule(new CloseDialogTask(), 1500);
    }

    private void customizeUI() {
        BurpExtender.getCallbacks().customizeUiComponent(this);
    }

    class CloseDialogTask extends TimerTask {

        @Override
        public void run() {
            statusMessage.setText("Enter JSONPath");
        }
    }

    /**
     * Converts null Objects to empty String ("").
     *
     * @param input The object to test for null-ness
     * @return Object Empty String if the input was null, the input unchanged
     * otherwise
     */
    public static String noNulls(Object input) {
        if (input == null) {
            return "null";
        }

        return input.toString();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jsonPathEntry;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JList<String> resultList;
    private javax.swing.JLabel statusMessage;
    // End of variables declaration//GEN-END:variables
}