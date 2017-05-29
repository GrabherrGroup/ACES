/*  
    PiiL: Pathway Interactive vIsualization tooL
    Copyright (C) 2015  Behrooz Torabi Moghadam

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/* **** This code has been developed based on VectorGraphics package of the FreeHEP Java Library
       available at http://java.freehep.org/vectorgraphics/index.html  **** */
       
package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.freehep.swing.layout.TableLayout;
//import org.freehep.graphicsbase.swing.ErrorDialog;
//import org.freehep.graphicsbase.swing.layout.TableLayout;
import org.freehep.util.export.ExportFileType;
import org.freehep.util.export.ExportFileTypeGroups;


public class CustomExport extends JOptionPane
{
private static final long serialVersionUID = -6297626389977751810L;
private static final String rootKey = CustomExport.class.getName();
private static final String SAVE_AS_TYPE = rootKey +".SaveAsType";
private static final String SAVE_AS_FILE = rootKey +".SaveAsFile";
final ImageIcon logoIcon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));

/**
* Set the Properties object to be used for storing/restoring
* user preferences. If not called user preferences will not be
* saved.
* @param properties The Properties to use for user preferences
*/
public void setUserProperties(Properties properties)
{
 props = properties;
}
/**
* Register an export file type.
*/
public void addExportFileType(ExportFileType fileType)
{
 list.addElement(fileType);
}
 
@SuppressWarnings("unchecked")
public void addAllExportFileTypes()
{
	   ExportFileTypeGroups groups = new ExportFileTypeGroups(ExportFileType.getExportFileTypes());
  for (Iterator<String> i=groups.getGroupNames().iterator(); i.hasNext(); ) {
	   String group = i.next();
      List<ExportFileType> exportTypes = groups.getExportFileTypes(group);
      if (exportTypes.size() > 0) {
          list.add(new JLabel(groups.getLabel(group), SwingConstants.CENTER));
          Collections.sort(exportTypes);
          for (Iterator<ExportFileType> j=exportTypes.iterator(); j.hasNext(); ) {
              addExportFileType(j.next());
          }
      }
  }
}
/**
* Creates a new instance of ExportDialog with all the standard export filetypes.
*/
public CustomExport()
{
 this(null);
}
/**
* Creates a new instance of ExportDialog with all the standard export filetypes.
* @param creator The "creator" to be written into the header of the file (may be null)
*/
public CustomExport(String creator)
{
 this(creator, true);
}
/**
* Creates a new instance of ExportDialog.
* @param creator The "creator" to be written into the header of the file (may be null)
* @param addAllExportFileTypes If true registers all the standard export filetypes
*/
public CustomExport(String creator, boolean addAllExportFileTypes)
{
 super(null,JOptionPane.PLAIN_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
 this.creator = creator;

 try
 {
    baseDir = System.getProperty("user.home");
 }
 catch (SecurityException x) { trusted = false; }

 ButtonListener bl = new ButtonListener();

 JPanel panel = new JPanel(new TableLayout());

 if (trusted)
 {
    panel.add("* * [5 5 5 5] w", file);
    panel.add("* * * 1 [5 5 5 5] wh", browse);
 }
 type = new JComboBox(list);
 type.setMaximumRowCount(16);      // rather than 8
 panel.add("* * 1 1 [5 5 5 5] w", type);

 panel.add("* * * 1 [5 5 5 5] wh", advanced);

 browse.addActionListener(bl);
 advanced.addActionListener(bl);
 type.setRenderer(new SaveAsRenderer());
 type.addActionListener(bl);

 setMessage(panel);

 if (addAllExportFileTypes) addAllExportFileTypes();
}

/**
* Show the dialog.
* @param parent The parent for the dialog
* @param title The title for the dialog
* @param target The component to be saved.
* @param size The target size to be used for export.
* @param defFile The default file name to use.
*/
public void showExportDialog(Component parent, String title, Component target, Dimension size, String defFile)
{
	   // NOTE: same, and used in AbstractExportFileType
	   props.setProperty("size-w", String.valueOf(size.width));
	   props.setProperty("size-h", String.valueOf(size.height));
	   showExportDialog(parent, title, target, defFile);
}

/**
* Show the dialog.
* @param parent The parent for the dialog
* @param title The title for the dialog
* @param target The component to be saved.
* @param defFile The default file name to use.
*/
public void showExportDialog(Component parent, String title, Component target, String defFile){

	this.component = target;
	// NOTE: a label is always first
	if (list.size() > 0) type.setSelectedIndex(1);
	String dType = props.getProperty(SAVE_AS_TYPE);
	if (dType != null){
		for (int i=0; i<list.size(); i++){
			Object obj = list.elementAt(i);
			if (obj instanceof ExportFileType){
				ExportFileType saveAs = (ExportFileType)obj;
				if (saveAs.getFileFilter().getDescription().equals(dType)){
					type.setSelectedItem(saveAs);
					break;
				}
			}
		}
	}
	advanced.setEnabled(currentType() != null && currentType().hasOptionPanel());
	if (trusted){
		String saveFile = props.getProperty(SAVE_AS_FILE);
		if (saveFile != null) {
			baseDir = new File(saveFile).getParent();
			defFile = saveFile;
		} 
		else {
			defFile = baseDir+File.separator+defFile;
		}
    File f = new File(defFile);
    if (currentType() != null) f = currentType().adjustFilename(f, currentType().getFileExtension(f), props);
    file.setText(f.toString());
	}
	else{
		file.setEnabled(false);
		browse.setEnabled(false);
	}

	JDialog dlg = createDialog(parent,title);
	dlg.pack();
	dlg.setVisible(true);
}
private ExportFileType currentType()
{
  return (ExportFileType)type.getSelectedItem();
}
/**
* Called to open a "file browser". Override this method to provide
* special handling (e.g. in a WebStart app)
* @return The full name of the selected file, or null if no file selected
*/
protected String selectFile()
{
 JFileChooser dlg = new JFileChooser();
 String f = file.getText();
 if (f != null) dlg.setSelectedFile(new File(f));
 dlg.setFileFilter(currentType().getFileFilter());
 if (dlg.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
	   
   return dlg.getSelectedFile().getAbsolutePath();
 } else {
   return null;
 }
}
/**
* Called to acually write out the file.
* Override this method to provide special handling (e.g. in a WebStart app)
* @return true if the file was written, or false to cancel operation
*/
protected boolean writeFile(final Component component, final ExportFileType t) throws IOException
{
 final File f = new File(file.getText());
 if (f.exists())
 {
    int ok = JOptionPane.showConfirmDialog(this,"Replace existing file?");
    if (ok != JOptionPane.OK_OPTION) return false;
 }
 
 final JDialog dialog = new JDialog(ACES.bodyFrame, "Export to image",ModalityType.APPLICATION_MODAL);
	dialog.setUndecorated(true);
	JProgressBar progressBar = new JProgressBar();
	progressBar.setIndeterminate(true);
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(progressBar, BorderLayout.CENTER);
	panel.add(new JLabel(" Exporting to image file ...  "), BorderLayout.PAGE_START);
	dialog.add(panel);
	dialog.pack();
	dialog.setLocationRelativeTo(ACES.bodyFrame);
	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		@Override
		protected Void doInBackground() {
				try {
					t.exportToFile(f,component,ACES.bodyFrame,props,creator);
					JOptionPane.showMessageDialog(ACES.bodyFrame, "Exported successfully!","Done",0,logoIcon);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				props.put(SAVE_AS_FILE,file.getText());
				props.put(SAVE_AS_TYPE,currentType().getFileFilter().getDescription());
			return null;
		}

		@Override
		protected void done() {
			dialog.dispose();
		}
	};
	worker.execute();
	dialog.setVisible(true);
	
 
 return true;
}
public void setValue(Object value)
{
 if (value instanceof Integer && ((Integer) value).intValue() == OK_OPTION)
 {
    try
    {
  	  
       if (!writeFile(component,currentType())) return;
    }
    catch (Throwable x)
    {
  	  JOptionPane.showMessageDialog(ACES.bodyFrame, "Error writing the graphics file!","Error",0,icon);
//       ErrorDialog.showErrorDialog(this,"Error writing graphics file",x);
       return;
    }
 }
 super.setValue(value);
 
}

private String creator;
private JButton browse = new JButton("Browse...");
private JButton advanced = new JButton("Options...");
private JTextField file = new JTextField(40);
private JComboBox type;
private Component component;
private boolean trusted = true;
private Vector<Object> list = new Vector<Object>();
private Properties props = new Properties();
private String baseDir = null;

private class ButtonListener implements ActionListener
{
 private ExportFileType previousType = null;

 public void actionPerformed(ActionEvent e)
 {
    Object source = e.getSource();
    if (source == browse)
    {
       String fileName = selectFile();
       if (fileName != null) {
           if (currentType() != null) {
               File f = new File(fileName);
               currentType().adjustFilename(f, currentType().getFileExtension(f), props);
               file.setText(f.getPath());
           } else {
               file.setText(fileName);
           }
       }
    }
    else if (source == advanced)
    {
        if (currentType() != null) {
           JPanel panel = currentType().createOptionPanel(props);
           int rc = JOptionPane.showConfirmDialog(CustomExport.this,panel,"Options for "+currentType().getDescription(),
                                         JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, icon);
           if (rc == JOptionPane.OK_OPTION) {
               currentType().applyChangedOptions(panel, props);
               File f1 = new File(file.getText());
               File f2 = currentType().adjustFilename(f1, currentType().getFileExtension(f1), props);
               if (!f1.equals(f2) && file.isEnabled()) file.setText(f2.toString());
           }
        }
    }
    else if (source == type)
    {
        if (type.getSelectedItem() instanceof ExportFileType) {
            if (previousType == null) previousType = currentType();
            advanced.setEnabled(currentType().hasOptionPanel());
            File f1 = new File(file.getText());
            File f2 = currentType().adjustFilename(f1, previousType.getFileExtension(f1), props);
            if (!f1.equals(f2) && file.isEnabled()) file.setText(f2.toString());
            previousType = currentType();
        } else {
            // keep old selection
            type.setSelectedItem(previousType);
        }
    }
 }
}

private static class SaveAsRenderer extends DefaultListCellRenderer
{
 public Component getListCellRendererComponent(JList list,
                                         Object value,
                                         int index,
                                         boolean isSelected,
                                         boolean cellHasFocus)
 {
    super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
    if (value instanceof ExportFileType)
    {
       this.setText(((ExportFileType) value).getFileFilter().getDescription());
    } else if (value instanceof JLabel) {
       return (Component)value;
    }
    return this;
 }
}
}


