package visualization;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;


public class HeatChart {
	

	public static final double SCALE_LOGARITHMIC = 0.3;

	public static final double SCALE_LINEAR = 1.0;
	
	public static final double SCALE_EXPONENTIAL = 3;
	
	// x, y, z data values.
	private double[][] zValues;
	private Object[] xValues;
	private Object[] yValues;
	
	private boolean xValuesHorizontal;
	private boolean yValuesHorizontal;
	
	// General chart settings.
	private Dimension cellSize;
	private Dimension chartSize;
	private int margin;
	private Color backgroundColour;
	
	// Title settings.
	private String title;
	private Font titleFont;
	private Color titleColour;
	private Dimension titleSize;
	private int titleAscent;
	private int[] labelIndex;
	
	// Axis settings.
	private int axisThickness;
	private Color axisColour;
	private Font axisLabelsFont;
	private Color axisLabelColour;
	private String xAxisLabel;
	private String yAxisLabel;
	private Color axisValuesColour;
	private Font axisValuesFont; // The font size will be considered the maximum font size - it may be smaller if needed to fit in.
	private int xAxisValuesFrequency;
	private int yAxisValuesFrequency;
	private boolean showXAxisValues;
	private boolean showYAxisValues;
	
	// Generated axis properties.
	private int xAxisValuesHeight;
	private int xAxisValuesWidthMax;
	
	private int yAxisValuesHeight;
	private int yAxisValuesAscent;
	private int yAxisValuesWidthMax;
	
	private Dimension xAxisLabelSize;
	private int xAxisLabelDescent;
	
	private Dimension yAxisLabelSize;
	private int yAxisLabelAscent;
	
	// Heat map colour settings.
	private Color highValueColour;
	private Color lowValueColour;
	
	// How many RGB steps there are between the high and low colours.
	private int colourValueDistance;
	
	private double lowValue;
	private double highValue;
	
	// Key co-ordinate positions.
	private Point heatMapTL;
	private Point heatMapBR;
	private Point heatMapC;
	
	// Heat map dimensions.
	private Dimension heatMapSize;
	
	// Control variable for mapping z-values to colours.
	private double colourScale;
	
	
	public HeatChart(double[][] zValues,int[] labelIndex) {
		this(zValues, min(zValues), max(zValues));
		this.labelIndex = labelIndex;
	}
	

	public HeatChart(double[][] zValues, double low, double high) {
		this.zValues = zValues;
		this.lowValue = low;
		this.highValue = high;
		
		// Default x/y-value settings.
		setXValues(0, 1);
		setYValues(0, 1);
		
		// Default chart settings.
		this.cellSize = new Dimension(20, 20);
		this.margin = 20;
		this.backgroundColour = Color.WHITE;
		
		// Default title settings.
		this.title = null;
		this.titleFont = new Font("Sans-Serif", Font.BOLD, 16);
		this.titleColour = Color.BLACK;
		
		// Default axis settings.
		this.xAxisLabel = null;
		this.yAxisLabel = null;
		this.axisThickness = 2;
		this.axisColour = Color.BLACK;
		this.axisLabelsFont = new Font("Sans-Serif", Font.PLAIN, 12);
		this.axisLabelColour = Color.BLACK;
		this.axisValuesColour = Color.BLACK;
		this.axisValuesFont = new Font("Sans-Serif", Font.PLAIN, 10);
		this.xAxisValuesFrequency = 1;
		this.xAxisValuesHeight = 0;
		this.xValuesHorizontal = false;
		this.showXAxisValues = true;
		this.showYAxisValues = true;
		this.yAxisValuesFrequency = 1;
		this.yAxisValuesHeight = 0;
		this.yValuesHorizontal = true;
		
		// Default heatmap settings.
		this.highValueColour = Color.BLACK;
		this.lowValueColour = Color.WHITE;
		this.colourScale = SCALE_LINEAR;
		
		updateColourDistance();
	}
	

	public double getLowValue() {
		return lowValue;
	}
	

	public double getHighValue() {
		return highValue;
	}
	
	
	public double[][] getZValues() {
		return zValues;
	}
	
	
	public void setZValues(double[][] zValues) {
		setZValues(zValues, min(zValues), max(zValues));
	}
	
	
	public void setZValues(double[][] zValues, double low, double high) {
		this.zValues = zValues;
		this.lowValue = low;
		this.highValue = high;
	}
	
	
	public void setXValues(double xOffset, double xInterval) {		
		// Update the x-values according to the offset and interval.
		xValues = new Object[zValues[0].length];
		for (int i=0; i<zValues[0].length; i++) {
			xValues[i] = xOffset + (i * xInterval);
		}
	}
	
	
	public void setXValues(Object[] xValues) {
		this.xValues = xValues;
	}
	
	
	public void setYValues(double yOffset, double yInterval) {
		// Update the y-values according to the offset and interval.
		yValues = new Object[zValues.length];
		for (int i=0; i<zValues.length; i++) {
			yValues[i] = yOffset + (i * yInterval);
		}
	}
	
	
	public void setYValues(Object[] yValues) {
		this.yValues = yValues;
	}
	
	public Object[] getXValues() {
		return xValues;
	}
	
	public Object[] getYValues() {
		return yValues;
	}

	public void setXValuesHorizontal(boolean xValuesHorizontal) {
		this.xValuesHorizontal = xValuesHorizontal;
	}
	
	public boolean isXValuesHorizontal() {
		return xValuesHorizontal;
	}
	
	public void setYValuesHorizontal(boolean yValuesHorizontal) {
		this.yValuesHorizontal = yValuesHorizontal;
	}
	
	public boolean isYValuesHorizontal() {
		return yValuesHorizontal;
	}
	
	public void setCellWidth(int cellWidth) {
		setCellSize(new Dimension(cellWidth, cellSize.height));
	}
	
	public int getCellWidth() {
		return cellSize.width;
	}
		
	public void setCellSize(Dimension cellSize) {
		this.cellSize = cellSize;
	}

	public Dimension getCellSize() {
		return cellSize;
	}
	
	public Dimension getChartSize() {
		return chartSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXAxisLabel() {
		return xAxisLabel;
	}

	public void setXAxisLabel(String xAxisLabel) {
		this.xAxisLabel = xAxisLabel;
	}


	public String getYAxisLabel() {
		return yAxisLabel;
	}

	
	public void setYAxisLabel(String yAxisLabel) {
		this.yAxisLabel = yAxisLabel;
	}

	
	public int getChartMargin() {
		return margin;
	}

	
	public void setChartMargin(int margin) {
		this.margin = margin;
	}

	
	public Color getBackgroundColour() {
		return backgroundColour;
	}

	
	public void setBackgroundColour(Color backgroundColour) {
		if (backgroundColour == null) {
			backgroundColour = Color.WHITE;
		}
		
		this.backgroundColour = backgroundColour;
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	public Color getTitleColour() {
		return titleColour;
	}

	public void setTitleColour(Color titleColour) {
		this.titleColour = titleColour;
	}

	public int getAxisThickness() {
		return axisThickness;
	}

	public void setAxisThickness(int axisThickness) {
		this.axisThickness = axisThickness;
	}

	public Color getAxisColour() {
		return axisColour;
	}

	public void setAxisColour(Color axisColour) {
		this.axisColour = axisColour;
	}

	public Font getAxisLabelsFont() {
		return axisLabelsFont;
	}

	public void setAxisLabelsFont(Font axisLabelsFont) {
		this.axisLabelsFont = axisLabelsFont;
	}

	public Color getAxisLabelColour() {
		return axisLabelColour;
	}

	public void setAxisLabelColour(Color axisLabelColour) {
		this.axisLabelColour = axisLabelColour;
	}

	public Font getAxisValuesFont() {
		return axisValuesFont;
	}

	public void setAxisValuesFont(Font axisValuesFont) {
		this.axisValuesFont = axisValuesFont;
	}

	public Color getAxisValuesColour() {
		return axisValuesColour;
	}

	public void setAxisValuesColour(Color axisValuesColour) {
		this.axisValuesColour = axisValuesColour;
	}
	
	public int getXAxisValuesFrequency() {
		return xAxisValuesFrequency;
	}

	public void setXAxisValuesFrequency(int axisValuesFrequency) {
		this.xAxisValuesFrequency = axisValuesFrequency;
	}

	public int getYAxisValuesFrequency() {
		return yAxisValuesFrequency;
	}

	public void setYAxisValuesFrequency(int axisValuesFrequency) {
		yAxisValuesFrequency = axisValuesFrequency; 
	}

	public boolean isShowXAxisValues() {
		//TODO Could get rid of these flags and use a frequency of -1 to signal no values.
		return showXAxisValues;
	}

	public void setShowXAxisValues(boolean showXAxisValues) {
		this.showXAxisValues = showXAxisValues;
	}

	public boolean isShowYAxisValues() {
		return showYAxisValues;
	}

	public void setShowYAxisValues(boolean showYAxisValues) {
		this.showYAxisValues = showYAxisValues;
	}

	public Color getHighValueColour() {
		return highValueColour;
	}

	public void setHighValueColour(Color highValueColour) {
		this.highValueColour = highValueColour;
		
		updateColourDistance();
	}

	public Color getLowValueColour() {
		return lowValueColour;
	}

	public void setLowValueColour(Color lowValueColour) {
		this.lowValueColour = lowValueColour;
		
		updateColourDistance();
	}

	public double getColourScale() {
		return colourScale;
	}

	public void setColourScale(double colourScale) {
		this.colourScale = colourScale;
	}

	/*
	 * Calculate and update the field for the distance between the low colour 
	 * and high colour. The distance is the number of steps between one colour 
	 * and the other using an RGB coding with 0-255 values for each of red, 
	 * green and blue. So the maximum colour distance is 255 + 255 + 255.
	 */
	private void updateColourDistance() {
		int r1 = lowValueColour.getRed();
		int g1 = lowValueColour.getGreen();
		int b1 = lowValueColour.getBlue();
		int r2 = highValueColour.getRed();
		int g2 = highValueColour.getGreen();
		int b2 = highValueColour.getBlue();
		
		colourValueDistance = Math.abs(r1 - r2);
		colourValueDistance += Math.abs(g1 - g2);
		colourValueDistance += Math.abs(b1 - b2);
	}


	public void saveToFile(File outputFile) throws IOException, DocumentException {
		String filename = outputFile.getName();

		int extPoint = filename.lastIndexOf('.');

		if (extPoint < 0) {
			throw new IOException("Illegal filename, no extension used.");
		}

		// Determine the extension of the filename.
		String ext = filename.substring(extPoint + 1);
		
		// Handle jpg without transparency.
		if (ext.toLowerCase().equals("jpg") || ext.toLowerCase().equals("jpeg")) {
			BufferedImage chart = (BufferedImage) getChartImage(false);

			// Save our graphic.
			saveGraphicJpeg(chart, outputFile, 1.0f);
		}
		else if(ext.toLowerCase().equals("pdf")){
			Document doc = new Document();
		    File file = new File("heatmap_temp_output.png");

			BufferedImage chart = (BufferedImage) getChartImage(true);
			ImageIO.write(chart, ext, file);
			PdfWriter.getInstance(doc, new FileOutputStream(outputFile.getCanonicalPath()));

			doc.open();
            com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance(file.getCanonicalPath());
            image.scaleToFit(500,500);
            doc.add(image);
            doc.close();
            file.delete();
		}
		else if(ext.toLowerCase().equals("svg")){
			File file = new File("heatmap_temp_output.png");
		    BufferedImage chart = ImageIO.read(file);

		    Graphics2D g2d = (Graphics2D)chart.getGraphics();
		    g2d.drawImage(chart,0,0,null);
		

		        
		        JPanel plot = new JPanel();
		        plot.paint(g2d);
		        SVGGraphics2D g2 = new SVGGraphics2D(plot.getWidth(), plot.getHeight());
			      plot.paintComponents(g2);
			       
			        try {
			            SVGUtils.writeToSVG(outputFile, g2.getSVGElement());
			        } catch (IOException ex) {
			            System.err.println(ex);
			        }

		}
		else {
			BufferedImage chart = (BufferedImage) getChartImage(true);
			
			ImageIO.write(chart, ext, outputFile);
		}
	}
	
	private void saveGraphicJpeg(BufferedImage chart, File outputFile, float quality) throws IOException {
		// Setup correct compression for jpeg.
		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) iter.next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(quality);
		
		// Output the image.
		FileImageOutputStream output = new FileImageOutputStream(outputFile);
		writer.setOutput(output);
		IIOImage image = new IIOImage(chart, null, null);
		writer.write(null, image, iwp);
		writer.dispose();
		
	}
	
	public Image getChartImage(boolean alpha) {
		// Calculate all unknown dimensions.
		measureComponents();
		updateCoordinates();
		
		// Determine image type based upon whether require alpha or not.
		// Using BufferedImage.TYPE_INT_ARGB seems to break on jpg.
		int imageType = (alpha ? BufferedImage.TYPE_4BYTE_ABGR : BufferedImage.TYPE_3BYTE_BGR);
		
		// Create our chart image which we will eventually draw everything on.
		BufferedImage chartImage = new BufferedImage(chartSize.width, chartSize.height, imageType);
		Graphics2D chartGraphics = chartImage.createGraphics();
		
		// Use anti-aliasing where ever possible.
		chartGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
									   RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Set the background.
		chartGraphics.setColor(backgroundColour);
		chartGraphics.fillRect(0, 0, chartSize.width, chartSize.height);
		
		// Draw the title.
		drawTitle(chartGraphics);
		
		// Draw the heatmap image.
		drawHeatMap(chartGraphics, zValues,this.labelIndex);
		
		// Draw the axis labels.
		drawXLabel(chartGraphics);
		drawYLabel(chartGraphics);
		
		// Draw the axis bars.
		drawAxisBars(chartGraphics);
		
		// Draw axis values.
		drawXValues(chartGraphics);
		drawYValues(chartGraphics);
		
		return chartImage;
	}
	
	public Image getChartImage() {
		return getChartImage(false);
	}
	
	/*
	 * Calculates all unknown component dimensions.
	 */
	private void measureComponents() {
		//TODO This would be a good place to check that all settings have sensible values or throw illegal state exception.
		
		//TODO Put this somewhere so it only gets created once.
		BufferedImage chartImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D tempGraphics = chartImage.createGraphics();
		
		// Calculate title dimensions.
		if (title != null) {
			tempGraphics.setFont(titleFont);
			FontMetrics metrics = tempGraphics.getFontMetrics();
			titleSize = new Dimension(metrics.stringWidth(title), metrics.getHeight());
			titleAscent = metrics.getAscent();
		} else {
			titleSize = new Dimension(0, 0);
		}
		
		// Calculate x-axis label dimensions.
		if (xAxisLabel != null) {
			tempGraphics.setFont(axisLabelsFont);
			FontMetrics metrics = tempGraphics.getFontMetrics();
			xAxisLabelSize = new Dimension(metrics.stringWidth(xAxisLabel), metrics.getHeight());
			xAxisLabelDescent = metrics.getDescent();
		} else {
			xAxisLabelSize = new Dimension(0, 0);
		}
		
		// Calculate y-axis label dimensions.
		if (yAxisLabel != null) {
			tempGraphics.setFont(axisLabelsFont);
			FontMetrics metrics = tempGraphics.getFontMetrics();
			yAxisLabelSize = new Dimension(metrics.stringWidth(yAxisLabel), metrics.getHeight());
			yAxisLabelAscent = metrics.getAscent();
		} else {
			yAxisLabelSize = new Dimension(0, 0);
		}
		
		// Calculate x-axis value dimensions.
		if (showXAxisValues) {
			tempGraphics.setFont(axisValuesFont);
			FontMetrics metrics = tempGraphics.getFontMetrics();
			xAxisValuesHeight = metrics.getHeight();
			xAxisValuesWidthMax = 0;
			for (Object o: xValues) {
				int w = metrics.stringWidth(o.toString());
				if (w > xAxisValuesWidthMax) {
					xAxisValuesWidthMax = w;
				}
			}
		} else {
			xAxisValuesHeight = 0;
		}
		
		// Calculate y-axis value dimensions.
		if (showYAxisValues) {
			tempGraphics.setFont(axisValuesFont);
			FontMetrics metrics = tempGraphics.getFontMetrics();
			yAxisValuesHeight = metrics.getHeight();
			yAxisValuesAscent = metrics.getAscent();
			yAxisValuesWidthMax = 0;
			for (Object o: yValues) {
				int w = metrics.stringWidth(o.toString());
				if (w > yAxisValuesWidthMax) {
					yAxisValuesWidthMax = w;
				}
			}
		} else {
			yAxisValuesHeight = 0;
		}
		
		// Calculate heatmap dimensions.
		int heatMapWidth = (zValues[0].length * cellSize.width);
		int heatMapHeight = ((zValues.length+1) * cellSize.height);
		heatMapSize = new Dimension(heatMapWidth, heatMapHeight);
		
		int yValuesHorizontalSize = 0;
		if (yValuesHorizontal) {
			yValuesHorizontalSize = yAxisValuesWidthMax;
		} else {
			yValuesHorizontalSize = yAxisValuesHeight;
		}
		
		int xValuesVerticalSize = 0;
		if (xValuesHorizontal) {
			xValuesVerticalSize = xAxisValuesHeight;
		} else {
			xValuesVerticalSize = xAxisValuesWidthMax;
		}
		
		// Calculate chart dimensions.
		int chartWidth = heatMapWidth + (2 * margin) + yAxisLabelSize.height + yValuesHorizontalSize + axisThickness;
		int chartHeight = heatMapHeight + (2 * margin) + xAxisLabelSize.height + xValuesVerticalSize + titleSize.height + axisThickness;
		chartSize = new Dimension(chartWidth, chartHeight);
	}
	
	/*
	 * Calculates the co-ordinates of some key positions.
	 */
	private void updateCoordinates() {
		// Top-left of heat map.
		int x = margin + axisThickness + yAxisLabelSize.height;
		x += (yValuesHorizontal ? yAxisValuesWidthMax : yAxisValuesHeight);
		int y = titleSize.height + margin;
		heatMapTL = new Point(x, y);

		// Top-right of heat map.
		x = heatMapTL.x + heatMapSize.width;
		y = heatMapTL.y + heatMapSize.height;
		heatMapBR = new Point(x, y);
		
		// Centre of heat map.
		x = heatMapTL.x + (heatMapSize.width / 2);
		y = heatMapTL.y + (heatMapSize.height / 2);
		heatMapC = new Point(x, y);
	}
	
	/*
	 * Draws the title String on the chart if title is not null.
	 */
	private void drawTitle(Graphics2D chartGraphics) {
		if (title != null) {			
			// Strings are drawn from the baseline position of the leftmost char.
			int yTitle = (margin/2) + titleAscent;
			int xTitle = (chartSize.width/2) - (titleSize.width/2);

			chartGraphics.setFont(titleFont);
			chartGraphics.setColor(titleColour);
			chartGraphics.drawString(title, xTitle, yTitle);
		}
	}
	
	/*
	 * Creates the actual heatmap element as an image, that can then be drawn 
	 * onto a chart.
	 */
	private void drawHeatMap(Graphics2D chartGraphics, double[][] data,int[] labelsIndex) {
		// Calculate the available size for the heatmap.
		int noYCells = data.length;
		int noXCells = data[0].length;
		
		//double dataMin = min(data);
		//double dataMax = max(data);

		BufferedImage heatMapImage = new BufferedImage(heatMapSize.width, heatMapSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D heatMapGraphics = heatMapImage.createGraphics();
		
		for (int x=0; x<noXCells; x++) {
			for (int y=0; y<noYCells; y++) {
				// Set colour depending on zValues.
				heatMapGraphics.setColor(getCellColour(data[y][x], lowValue, highValue));
				
				int cellX = x*cellSize.width;
				int cellY = y*cellSize.height;
				
				heatMapGraphics.fillRect(cellX, cellY, cellSize.width, cellSize.height);
			}
			if (labelsIndex[x] == 3)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.33, 1, (float) 0.4));
			else if (labelsIndex[x] == 2)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.875, (float) 0.8, (float) 1.0));
			else if (labelsIndex[x] == 1)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.7, 1, (float) 1));
			else if (labelsIndex[x] == 4)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.083, (float) 0.5, (float) 0.8));
			else if (labelsIndex[x] == 5)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.583, (float) 0.5, (float) 0.8));
			else if (labelsIndex[x] == 6)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.25, (float) 0.4, (float) 1.0));
			else if (labelsIndex[x] == 7)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.44, (float) 0.6, (float) 0.5));
			else if (labelsIndex[x] == 8)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.0, 1, (float) 0.6));
			else if (labelsIndex[x] == 9)
				heatMapGraphics.setColor(Color.getHSBColor((float) 0.75, (float) 0.5, (float) 0.6));
			else if(labelsIndex[x] == 0)
				heatMapGraphics.setColor(Color.WHITE);
			else
				heatMapGraphics.setColor(Color.BLACK);
	
			
			int cellX = x*cellSize.width;
			int cellY = noYCells*cellSize.height;
			
			heatMapGraphics.fillRect(cellX, cellY, cellSize.width, cellSize.height);
			
		}
		
		// Draw the heat map onto the chart.
		chartGraphics.drawImage(heatMapImage, heatMapTL.x, heatMapTL.y, heatMapSize.width, heatMapSize.height, null);
	}
	
	/*
	 * Draws the x-axis label string if it is not null.
	 */
	private void drawXLabel(Graphics2D chartGraphics) {
		if (xAxisLabel != null) {
			// Strings are drawn from the baseline position of the leftmost char.
			int yPosXAxisLabel = chartSize.height - (margin / 2) - xAxisLabelDescent;
			//TODO This will need to be updated if the y-axis values/label can be moved to the right.
			int xPosXAxisLabel = heatMapC.x - (xAxisLabelSize.width / 2);
			
			chartGraphics.setFont(axisLabelsFont);
			chartGraphics.setColor(axisLabelColour);
			chartGraphics.drawString(xAxisLabel, xPosXAxisLabel, yPosXAxisLabel);
		}
	}
	
	/*
	 * Draws the y-axis label string if it is not null.
	 */
	private void drawYLabel(Graphics2D chartGraphics) {
		if (yAxisLabel != null) {
			// Strings are drawn from the baseline position of the leftmost char.
			int yPosYAxisLabel = heatMapC.y + (yAxisLabelSize.width / 2);
			int xPosYAxisLabel = (margin / 2) + yAxisLabelAscent;
			
			chartGraphics.setFont(axisLabelsFont);
			chartGraphics.setColor(axisLabelColour);
			
			// Create 270 degree rotated transform.
			AffineTransform transform = chartGraphics.getTransform();
			AffineTransform originalTransform = (AffineTransform) transform.clone();
			transform.rotate(Math.toRadians(270), xPosYAxisLabel, yPosYAxisLabel);
			chartGraphics.setTransform(transform);
			
			// Draw string.
			chartGraphics.drawString(yAxisLabel, xPosYAxisLabel, yPosYAxisLabel);
			
			// Revert to original transform before rotation.
			chartGraphics.setTransform(originalTransform);
		}
	}
	
	
	private void drawAxisBars(Graphics2D chartGraphics) {
		if (axisThickness > 0) {
			chartGraphics.setColor(axisColour);
			
			// Draw x-axis.
			int x = heatMapTL.x - axisThickness;
			int y = heatMapBR.y;
			int width = heatMapSize.width + axisThickness;
			int height = axisThickness;
			chartGraphics.fillRect(x, y, width, height);
			
			// Draw y-axis.
			x = heatMapTL.x - axisThickness;
			y = heatMapTL.y;
			width = axisThickness;
			height = heatMapSize.height;
			chartGraphics.fillRect(x, y, width, height);
		}
	}
	
	private void drawXValues(Graphics2D chartGraphics) {
		if (!showXAxisValues) {
			return;
		}
		
		chartGraphics.setColor(axisValuesColour);
		
		for (int i=0; i<xValues.length; i++) {
			if (i % xAxisValuesFrequency != 0) {
				continue;
			}
			
			String xValueStr = xValues[i].toString();
			
			chartGraphics.setFont(axisValuesFont);
			FontMetrics metrics = chartGraphics.getFontMetrics();
			
			int valueWidth = metrics.stringWidth(xValueStr);
			
			if (xValuesHorizontal) {
				// Draw the value with whatever font is now set.
				int valueXPos = (i * cellSize.width) + ((cellSize.width / 2) - (valueWidth / 2));
				valueXPos += heatMapTL.x;
				int valueYPos = heatMapBR.y + metrics.getAscent() + 1;
				
				chartGraphics.drawString(xValueStr, valueXPos, valueYPos);
			} else {
				int valueXPos = heatMapTL.x + (i * cellSize.width) + ((cellSize.width / 2) + (xAxisValuesHeight / 2));
				int valueYPos = heatMapBR.y + axisThickness + valueWidth;
				
				// Create 270 degree rotated transform.
				AffineTransform transform = chartGraphics.getTransform();
				AffineTransform originalTransform = (AffineTransform) transform.clone();
				transform.rotate(Math.toRadians(270), valueXPos, valueYPos);
				chartGraphics.setTransform(transform);
				
				// Draw the string.
				chartGraphics.drawString(xValueStr, valueXPos, valueYPos);
				
				// Revert to original transform before rotation.
				chartGraphics.setTransform(originalTransform);
			}
		}
	}
	
	private void drawYValues(Graphics2D chartGraphics) {
		if (!showYAxisValues) {
			return;
		}
		
		chartGraphics.setColor(axisValuesColour);
		
		for (int i=0; i<yValues.length; i++) {
			if (i % yAxisValuesFrequency != 0) {
				continue;
			}
			
			String yValueStr = yValues[i].toString();
			
			chartGraphics.setFont(axisValuesFont);
			FontMetrics metrics = chartGraphics.getFontMetrics();
			
			int valueWidth = metrics.stringWidth(yValueStr);
			
			if (yValuesHorizontal) {
				// Draw the value with whatever font is now set.
				int valueXPos = margin + yAxisLabelSize.height + (yAxisValuesWidthMax - valueWidth);
				int valueYPos = heatMapTL.y + (i * cellSize.height) + (cellSize.height/2) + (yAxisValuesAscent/2);
				
				chartGraphics.drawString(yValueStr, valueXPos, valueYPos);
			} else {
				int valueXPos = margin + yAxisLabelSize.height + yAxisValuesAscent;
				int valueYPos = heatMapTL.y + (i * cellSize.height) + (cellSize.height/2) + (valueWidth/2);
				
				// Create 270 degree rotated transform.
				AffineTransform transform = chartGraphics.getTransform();
				AffineTransform originalTransform = (AffineTransform) transform.clone();
				transform.rotate(Math.toRadians(270), valueXPos, valueYPos);
				chartGraphics.setTransform(transform);
				
				// Draw the string.
				chartGraphics.drawString(yValueStr, valueXPos, valueYPos);
				
				// Revert to original transform before rotation.
				chartGraphics.setTransform(originalTransform);
			}
		}
	}

	private Color getCellColour(double data, double min, double max) {		
		double range = max - min;
		double position = data - min;

		// What proportion of the way through the possible values is that.
		double percentPosition = position / range;
		
		// Which colour group does that put us in.
		int colourPosition = getColourPosition(percentPosition);
		
		int r = lowValueColour.getRed();
		int g = lowValueColour.getGreen();
		int b = lowValueColour.getBlue();
		
		// Make n shifts of the colour, where n is the colourPosition.
		for (int i=0; i<colourPosition; i++) {
			int rDistance = r - highValueColour.getRed();
			int gDistance = g - highValueColour.getGreen();
			int bDistance = b - highValueColour.getBlue();
			
			if ((Math.abs(rDistance) >= Math.abs(gDistance))
						&& (Math.abs(rDistance) >= Math.abs(bDistance))) {
				// Red must be the largest.
				r = changeColourValue(r, rDistance);
			} else if (Math.abs(gDistance) >= Math.abs(bDistance)) {
				// Green must be the largest.
				g = changeColourValue(g, gDistance);
			} else {
				// Blue must be the largest.
				b = changeColourValue(b, bDistance);
			}
		}
		
		return new Color(r, g, b);
	}
	
	private int getColourPosition(double percentPosition) {
		return (int) Math.round(colourValueDistance * Math.pow(percentPosition, colourScale));
	}
	
	private int changeColourValue(int colourValue, int colourDistance) {
		if (colourDistance < 0) {
			return colourValue+1;
		} else if (colourDistance > 0) {
			return colourValue-1;
		} else {
			// This shouldn't actually happen here.
			return colourValue;
		}
	}
	
	public static double max(double[][] values) {
		double max = 0;
		for (int i=0; i<values.length; i++) {
			for (int j=0; j<values[i].length; j++) {
				max = (values[i][j] > max) ? values[i][j] : max;
			}			
		}
		return max;
	}

	public static double min(double[][] values) {
		double min = Double.MAX_VALUE;
		for (int i=0; i<values.length; i++) {
			for (int j=0; j<values[i].length; j++) {
				min = (values[i][j] < min) ? values[i][j] : min;
			}
		}
		return min;
	}

}
