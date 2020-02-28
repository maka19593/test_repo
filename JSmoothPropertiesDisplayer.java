import java.util.Enumeration;
import java.util.Properties;
import jsmooth.DriveInfo;
import jsmooth.Native;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.36
// 

public class JSmoothPropertiesDisplayer extends Frame
{
    private String[] m_args;
    private Label label1;
    private Button m_buttonClose;
    private TextArea m_text;
    
    public JSmoothPropertiesDisplayer(final String[] args) {
        this.m_args = args;
        this.initComponents();
        this.displayInformation();
    }
    
    private void initComponents() {
        this.label1 = new Label();
        this.m_text = new TextArea();
        this.m_buttonClose = new Button();
        this.setLayout(new GridBagLayout());
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                JSmoothPropertiesDisplayer.this.exitForm(windowEvent);
            }
        });
        this.label1.setAlignment(1);
        this.label1.setFont(new Font("Dialog", 0, 18));
        this.label1.setText("JSmooth Sample Program");
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 0;
        constraints.fill = 1;
        constraints.insets = new Insets(5, 0, 0, 0);
        this.add(this.label1, constraints);
        this.m_text.setEditable(false);
        final GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridwidth = 0;
        constraints2.fill = 1;
        constraints2.weightx = 1.0;
        constraints2.weighty = 1.0;
        constraints2.insets = new Insets(5, 5, 5, 5);
        this.add(this.m_text, constraints2);
        this.m_buttonClose.setLabel("Close");
        this.m_buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JSmoothPropertiesDisplayer.this.buttonCloseActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.add(this.m_buttonClose, gridBagConstraints);
        final Button comp = new Button("JNI Tests");
        this.add(comp, gridBagConstraints);
        comp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    final File file = new File("Z:/test");
                    System.out.println(file.getAbsoluteFile().toString());
                    System.out.println(file.getAbsoluteFile().getCanonicalPath().toString());
                    System.out.println("JNI Availability : " + Native.isAvailable());
                    if (Native.isAvailable()) {
                        final DriveInfo driveInfo = Native.getDriveInfo(file);
                        if (driveInfo.getDriveType() == 2) {}
                        if (driveInfo.getFreeSpaceForUser() < 67108864L) {}
                        System.out.println("DriveInfo: " + driveInfo + " = " + driveInfo.toString());
                        System.out.println("path: " + Native.getExecutablePath());
                        System.out.println("filename: " + Native.getExecutableName());
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.pack();
    }
    
    private void buttonCloseActionPerformed(final ActionEvent actionEvent) {
        System.exit(0);
    }
    
    private void exitForm(final WindowEvent windowEvent) {
        System.exit(87);
    }
    
    public static void main(final String[] array) throws Exception {
        new JSmoothPropertiesDisplayer(array).setVisible(true);
    }
    
    private void displayInformation() {
        final StringBuffer sb = new StringBuffer();
        sb.append("-- Sample --\n\n");
        sb.append("Current Directory: " + new File(".").getAbsolutePath() + "\n");
        sb.append("Arguments passed on the command line: " + this.m_args.length + " \n");
        for (int i = 0; i < this.m_args.length; ++i) {
            sb.append("" + i + ". " + this.m_args[i]);
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("Free Heap Memory: " + Runtime.getRuntime().freeMemory() + " bytes\n");
        sb.append("Total Heap Memory: " + Runtime.getRuntime().totalMemory() + " bytes\n");
        sb.append("\n");
        sb.append("System Properties:\n\n");
        final Properties properties = System.getProperties();
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            sb.append(s);
            sb.append(" = ");
            sb.append(properties.getProperty(s));
            sb.append("\n");
        }
        System.out.println(sb.toString());
        this.m_text.setText(sb.toString());
    }
}
