package org.math.plot;

import java.awt.event.*;
import java.io.*;
import java.security.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;
import org.math.plot.PlotPanel;
import org.math.plot.canvas.*;
import org.math.plot.canvas.PlotCanvas;
import icons.*;

/**
 * BSD License
 * 
 * @author Yann RICHET
 */
public class PlotToolBar extends JToolBar {

    // TODO redesign icons...
    private static final long serialVersionUID = 1L;
    protected ButtonGroup buttonGroup;
    protected JToggleButton buttonCenter;
    //protected JToggleButton buttonEdit;
    protected JToggleButton buttonZoom;
    protected JToggleButton buttonRotate;
    //protected JToggleButton buttonViewCoords;
    protected JButton buttonSetScales;
    protected JButton buttonDatas;
    protected JButton buttonSavePNGFile;
    protected JButton buttonReset;
    protected JButton buttonAdjustBounds;
    private boolean denySaveSecurity;
    private JFileChooser pngFileChooser;
    /** the currently selected PlotPanel */
    private PlotCanvas plotCanvas;
    private PlotPanel plotPanel;

    public PlotToolBar(PlotPanel pp) {
        plotPanel = pp;
        plotCanvas = pp.plotCanvas;

        try {
            pngFileChooser = new JFileChooser();
            pngFileChooser.setFileFilter(new FileFilter() {

                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".svg");
                }

                public String getDescription() {
                    return "Scalable Vector Graphics";
                }
            });
        } catch (AccessControlException ace) {
            denySaveSecurity = true;
        }

        buttonGroup = new ButtonGroup();

        buttonCenter = new JToggleButton(new ImageIcon(PlotPanel.class.getResource("/icons/center.png")));
        buttonCenter.setToolTipText("Center axis");
        buttonCenter.setSelected(plotCanvas.ActionMode == PlotCanvas.TRANSLATION);

        buttonZoom = new JToggleButton(new ImageIcon(PlotPanel.class.getResource("/icons/zoom.png")));
        buttonZoom.setToolTipText("Zoom");
        buttonZoom.setSelected(plotCanvas.ActionMode == PlotCanvas.ZOOM);

        //buttonEdit = new JToggleButton(new ImageIcon(PlotPanel.class.getResource("icons/edit.png")));
        //buttonEdit.setToolTipText("Edit mode");

        //buttonViewCoords = new JToggleButton(new ImageIcon(PlotPanel.class.getResource("icons/position.png")));
        //buttonViewCoords.setToolTipText("Highlight coordinates / Highlight plot");

        buttonSetScales = new JButton(new ImageIcon(PlotPanel.class.getResource("/icons/scale.png")));
        buttonSetScales.setToolTipText("Edit axis scales");

        buttonDatas = new JButton(new ImageIcon(PlotPanel.class.getResource("/icons/data.png")));
        buttonDatas.setToolTipText("Get data");

        buttonSavePNGFile = new JButton(new ImageIcon(PlotPanel.class.getResource("/icons/topngfile.png")));
        buttonSavePNGFile.setToolTipText("Save graphics in a .svg File");

        buttonReset = new JButton(new ImageIcon(PlotPanel.class.getResource("/icons/back.png")));
        buttonReset.setToolTipText("Reset zoom & axis");

        buttonAdjustBounds = new JButton(new ImageIcon(PlotPanel.class.getResource(plotCanvas.getAdjustBounds() ? "/icons/adjustbounds.png" : "/icons/noadjustbounds.png")));
        buttonAdjustBounds.setToolTipText("Auto-update/fix bounds");

        /*buttonEdit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        plotCanvas.ActionMode = PlotCanvas.EDIT;
        }
        });*/

        buttonZoom.setSelected(true);
        buttonZoom.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.ActionMode = PlotCanvas.ZOOM;
            }
        });

        buttonCenter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.ActionMode = PlotCanvas.TRANSLATION;
            }
        });

        /*buttonViewCoords.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        plotCanvas.setNoteCoords(buttonViewCoords.isSelected());
        }
        });*/

        buttonSetScales.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.displayScalesFrame();
            }
        });

        buttonDatas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.displayDataFrame();
            }
        });

        buttonSavePNGFile.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                choosePNGFile();
            }
        });

        buttonReset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.resetBase();
            }
        });

        buttonAdjustBounds.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                plotCanvas.setAdjustBounds(!plotCanvas.getAdjustBounds());
                ajustBoundsChanged();
            }
        });

        buttonGroup.add(buttonCenter);
        buttonGroup.add(buttonZoom);
        //buttonGroup.add(buttonEdit);

        add(buttonCenter, null);
        add(buttonZoom, null);
        add(buttonReset, null);
        //add(buttonViewCoords, null);
        add(buttonSetScales, null);
        if (adjustBoundsVisible) {
            add(buttonAdjustBounds, null);
        }
        //add(buttonEdit, null);
        add(buttonSavePNGFile, null);
        add(buttonDatas, null);

        if (!denySaveSecurity) {
            pngFileChooser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
						saveGraphicFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            });
        } else {
            buttonSavePNGFile.setEnabled(false);
        }

        //buttonEdit.setEnabled(plotCanvas.getEditable());

        //buttonViewCoords.setEnabled(plotCanvas.getNotable());

        // allow mixed (2D/3D) plots managed by one toolbar
        if (plotCanvas instanceof Plot3DCanvas) {
            if (buttonRotate == null) {
                buttonRotate = new JToggleButton(new ImageIcon(PlotPanel.class.getResource("/icons/rotation.png")));
                buttonRotate.setToolTipText("Rotate axes");

                buttonRotate.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        plotCanvas.ActionMode = Plot3DCanvas.ROTATION;
                    }
                });
                buttonGroup.add(buttonRotate);
                add(buttonRotate, null, 2);
                buttonRotate.setSelected(plotCanvas.ActionMode == Plot3DCanvas.ROTATION);
            } else {
                buttonRotate.setEnabled(true);
            }
        } else {
            if (buttonRotate != null) {
                // no removal/disabling just disable
                if (plotCanvas.ActionMode == Plot3DCanvas.ROTATION) {
                    plotCanvas.ActionMode = PlotCanvas.ZOOM;
                }
                buttonRotate.setEnabled(false);
            }
        }
    }

    void choosePNGFile() {
    	pngFileChooser.setSelectedFile(new File("plot.svg"));

        pngFileChooser.showSaveDialog(this);
        
    }

    void saveGraphicFile() throws IOException {
            File ff = pngFileChooser.getSelectedFile();
   
			SVGGraphics2D g2 = new SVGGraphics2D(plotPanel.getWidth(), plotPanel.getHeight());
			plotPanel.paint(g2);
      
			try {
			    SVGUtils.writeToSVG(ff, g2.getSVGElement());
			} catch (IOException ex) {
			    System.err.println(ex);
			}
			   // JOptionPane.showMessageDialog(null, "File has been saved","File Saved", JOptionPane.INFORMATION_MESSAGE);
 
    }
    boolean adjustBoundsVisible = true;

    public void viewAdjustBounds(boolean visible) {
        if (visible && !adjustBoundsVisible) {
            add(buttonAdjustBounds, null);
            adjustBoundsVisible = true;
        }
        if (!visible && adjustBoundsVisible) {
            remove(buttonAdjustBounds);
            adjustBoundsVisible = false;
        }
        ajustBoundsChanged();
    }

    public void ajustBoundsChanged() {
        buttonAdjustBounds.setIcon(new ImageIcon(PlotPanel.class.getResource(plotCanvas.getAdjustBounds() ? "/icons/adjustbounds.png" : "/icons/noadjustbounds.png")));
    }
}