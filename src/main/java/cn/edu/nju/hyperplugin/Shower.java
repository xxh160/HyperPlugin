package cn.edu.nju.hyperplugin;

import cn.edu.nju.hyperplugin.util.Cello;
import cn.edu.nju.hyperplugin.util.Config;
import cn.edu.nju.hyperplugin.util.SmartContract;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

@Getter
public class Shower extends SimpleToolWindowPanel {

    /*
     * tool window struct:
     * Shower:
     *  content:
     *      JPanel: contract api table
     *      JPanel: input
     *      JPanel: operations(open cello, show smart contract apis)
     */

    private JPanel content;

    private JPanel tools;
    private JButton openCello;
    private JButton showApis;

    private JPanel input;
    private JLabel celloPathLabel;
    private JTextField celloPathInput;

    private JScrollPane contract;
    private JBTable apis;

    private final Project project;
    private final Vector<String> title;

    public Shower(Project project) {
        super(false, true);
        this.project = project;
        this.title = new Vector<>();
        this.title.add("name");
        this.title.add("args");
        this.initContent();
    }

    private void updateTable() {
        String path = this.project.getBasePath() + Config.smartContractPath;
        Vector<Vector<String>> v = SmartContract.readApi(path);
        DefaultTableModel m = new DefaultTableModel(v, this.title);
        this.apis.setModel(m);
    }

    private void initTools() {
        this.openCello = new JButton();
        this.openCello.setText("Open Cello");
        this.openCello.addActionListener(e -> Cello.open(this.celloPathInput.getText()));
        this.tools.add(this.openCello);

        this.showApis = new JButton();
        this.showApis.setText("Show SC Apis");
        this.showApis.addActionListener(e -> this.updateTable());
        this.tools.add(this.showApis);
    }

    private void initApiTable() {
        this.apis = new JBTable();
        this.contract.setViewportView(this.apis);
        Vector<Vector<String>> content = new Vector<>();
        DefaultTableModel model = new DefaultTableModel(content, this.title);
        this.apis.setModel(model);
    }

    // init swing content
    private void initContent() {
        this.content = new JPanel(new BorderLayout());

        this.tools = new JPanel();
        this.content.add(this.tools, BorderLayout.NORTH);
        this.initTools();

        this.input = new JPanel(new FlowLayout());
        this.celloPathLabel = new JLabel("Custom Cello Path");
        this.celloPathInput = new JTextField();
        this.celloPathInput.setText(Config.celloUrl);
        this.input.add(this.celloPathLabel);
        this.input.add(this.celloPathInput);
        this.content.add(this.input, BorderLayout.SOUTH);

        this.contract = new JBScrollPane();
        this.content.add(this.contract, BorderLayout.CENTER);
        this.initApiTable();
    }

}
