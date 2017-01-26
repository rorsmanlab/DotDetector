import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.IJ;
import ij.gui.*;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.ImageStack.*;
import ij.WindowManager;
import ij.measure.Measurements;
import ij.measure.ResultsTable;
import ij.plugin.filter.PlugInFilter;
import ij.plugin.frame.RoiManager;
import ij.process.BinaryProcessor;
import ij.process.ByteProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import ij.plugin.*;
import ij.plugin.filter.*;
import ij.plugin.CanvasResizer;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.BackgroundSubtracter;
import ij.plugin.filter.GaussianBlur;
import ij.plugin.filter.MaximumFinder;
import ij.plugin.filter.ParticleAnalyzer;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;


//import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
 
// An AWT program inherits from the top-level container java.awt.Frame
public class DetectDots_A extends JFrame implements ActionListener {
	  
	
	
	
   private Label lblgbSigma1;    // Declare a Label component
   private Label lblaccuracy;    // Declare a Label component
   private Label lblcycles;    // Declare a Label component
   private Label lblnoise;    // Declare a Label component
   private Label lblgbSigma2;    // Declare a Label component
   private Label lbltolerance1;    // Declare a Label component
   private Label lbltolerance2;
   private Label lblreframe;    // Declare a Label component
   private Label lbllifetime;   
   private Label lblframe;   // Declare a Label component
   private Label lblelapsedtime;
   
   
   public static JTextField tfgbSigma1; // Declare a TextField component 
   public static JTextField tfaccuracy;
   public static JTextField tfcycles;
   public static JTextField tfnoise;
   public static JTextField tfgbSigma2;
   public static JTextField tftolerance1;// Declare a TextField component 
   public static JTextField tftolerance2;
   public static JTextField tfreframe;
   public static JTextField tflifetime;
   public static JTextField tfframe;
   public static JTextField tfelapsedtime;
   private JPanel panelSegmentation;
   private JPanel panelDotCount;
   private Label labelDotCount;
   private JButton btnDotCount;
   public static JCheckBox checkBoxPlotDotCount;
   private JPanel panelProgress;
   private Label labelProgress;
   private JTable table;
   private Label labelOutput;
   private Label label;
   private Label labelHeatmap;
   public static JProgressBar progressBar;
   private Label labelFFT;
   private JButton button;
   private JCheckBox checkBoxFourier;
   private Label label_3;
   private JTextField textField;
   private Label label_4;
   private JTextField textField_1;
   private Label label_5;
   private JTextField textField_2;
   private Label labelLoG;
   private JButton button_1;
   private JCheckBox checkBoxLoG;
   private Label label_7;
   private JTextField textField_3;
   private Label label_8;
   private JTextField textField_4;
   private Label label_9;
   private JTextField textField_5;
   private JSeparator separator;
   private JSeparator separator_1;
   public static JCheckBox checkBoxBordersDotCount;
   public static Canvas canvas;
   public static JPanel HeatMapPanel;
   public static JPanel DotsPanel;
   public static JPanel DotsDtPanel;
   



	  public static void updateBar(int newValue) {
	    progressBar.setValue(newValue);
	  }
	  
	
   
   // Constructor to setup GUI components and event handlers
   public DetectDots_A () {
	   
//	   pbar = new JProgressBar();
//	    pbar.setMinimum(MY_MINIMUM);
//	    pbar.setMaximum(MY_MAXIMUM);
//	    add(pbar);
	    
   	getContentPane().setBackground(SystemColor.menu);
      getContentPane().setLayout(null);
      
      labelHeatmap = new Label("dots");
      labelHeatmap.setBounds(289, 540, 35, 22);
      getContentPane().add(labelHeatmap);
      
      HeatMapPanel = new JPanel();
      HeatMapPanel.setBackground(Color.WHITE);
      HeatMapPanel.setBounds(289, 546, 526, 87);
      getContentPane().add(HeatMapPanel);
      
      label = new Label("dots");
      label.setBounds(289, 93, 35, 22);
      getContentPane().add(label);
      
      DotsPanel = new JPanel();
      DotsPanel.setBackground(Color.WHITE);
      DotsPanel.setBounds(289, 99, 526, 215);
      getContentPane().add(DotsPanel);
      
      Label labelDdt = new Label("d/dt");
      labelDdt.setBounds(298, 320, 25, 22);
      getContentPane().add(labelDdt);
      
      DotsDtPanel = new JPanel();
      DotsDtPanel.setBackground(Color.WHITE);
      DotsDtPanel.setBounds(289, 320, 526, 215);
      getContentPane().add(DotsDtPanel);
      
      labelOutput = new Label("data output");
      labelOutput.setBounds(297, 0, 79, 22);
      getContentPane().add(labelOutput);
      
      JDesktopPane desktopPane = new JDesktopPane();
      desktopPane.setBounds(91, 551, 1, 1);
      getContentPane().add(desktopPane);
      
      panelSegmentation = new JPanel();
      panelSegmentation.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      panelSegmentation.setBackground(SystemColor.menu);
      panelSegmentation.setBounds(10, 261, 263, 93);
      getContentPane().add(panelSegmentation);
            panelSegmentation.setLayout(null);
            
            Label labelSegmentation = new Label("Segmentation");
            labelSegmentation.setFont(new Font("Arial", Font.BOLD, 12));
            labelSegmentation.setBounds(10, 10, 86, 22);
            panelSegmentation.add(labelSegmentation);
      
            lblnoise = new Label("noise");
            lblnoise.setAlignment(Label.RIGHT);
            lblnoise.setBounds(139, 10, 46, 22);
            panelSegmentation.add(lblnoise);
      
      tfnoise = new JTextField("25", 10);
      tfnoise.setBounds(218, 10, 35, 20);
      panelSegmentation.add(tfnoise);
      tfnoise.setEditable(true);
      
      lblgbSigma2 = new Label("sigma");
      lblgbSigma2.setAlignment(Label.RIGHT);
      lblgbSigma2.setBounds(144, 37, 41, 22);
      panelSegmentation.add(lblgbSigma2);
      
      
      tfgbSigma2 = new JTextField("1", 10);
      tfgbSigma2.setBounds(218, 38, 35, 20);
      panelSegmentation.add(tfgbSigma2);
      tfgbSigma2.setEditable(true);
      
      lbltolerance1 = new Label("tolerance");
      lbltolerance1.setAlignment(Label.RIGHT);
      lbltolerance1.setBounds(123, 65, 62, 22);
      panelSegmentation.add(lbltolerance1);
      
            
            tftolerance1 = new JTextField("10", 10);
            tftolerance1.setBounds(218, 65, 35, 20);
            panelSegmentation.add(tftolerance1);
            tftolerance1.setEditable(true);
            
            JButton btnSegmentationApply = new JButton("Apply");
            btnSegmentationApply.setBounds(7, 65, 89, 23);
            panelSegmentation.add(btnSegmentationApply);
            btnSegmentationApply.addActionListener(new SegmentImage_A());
            
            JCheckBox chckbxSegmentationInclude = new JCheckBox("Include");
            chckbxSegmentationInclude.setBounds(6, 38, 86, 23);
            panelSegmentation.add(chckbxSegmentationInclude);
      
      JPanel panelDoG = new JPanel();
      panelDoG.setLayout(null);
      panelDoG.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      panelDoG.setBackground(SystemColor.menu);
      panelDoG.setBounds(10, 11, 263, 248);
      getContentPane().add(panelDoG);
      
      Label labelDoG = new Label("DoG");
      labelDoG.setFont(new Font("Arial", Font.BOLD, 12));
      labelDoG.setBounds(10, 3, 86, 22);
      panelDoG.add(labelDoG);
      
      JButton btnDoGApply = new JButton("Apply");
      btnDoGApply.setBounds(7, 58, 89, 23);
      panelDoG.add(btnDoGApply);
      btnDoGApply.addActionListener(new DoGOfImage_A());
      
      
      JCheckBox checkBoxDoGInclude = new JCheckBox("Include");
      checkBoxDoGInclude.setBounds(6, 31, 86, 23);
      panelDoG.add(checkBoxDoGInclude);
      // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
      // the components from left-to-right, and flow to next row from top-to-bottom.
 
      lblgbSigma1 = new Label("sigma");
      lblgbSigma1.setAlignment(Label.RIGHT);
      lblgbSigma1.setBounds(145, 3, 40, 22);
      panelDoG.add(lblgbSigma1);
      
      tfgbSigma1 = new JTextField("1", 10);
      tfgbSigma1.setBounds(201, 3, 52, 21);
      panelDoG.add(tfgbSigma1);
      tfgbSigma1.setEditable(true);
      
           lblaccuracy = new Label("blur accuracy");
           lblaccuracy.setAlignment(Label.RIGHT);
           lblaccuracy.setBounds(106, 30, 79, 22);
           panelDoG.add(lblaccuracy);
           
           tfaccuracy = new JTextField("0.0002", 10);
           tfaccuracy.setBounds(201, 30, 52, 22);
           panelDoG.add(tfaccuracy);
           tfaccuracy.setEditable(true);
           
                 lblcycles = new Label("DoG cycles");
                 lblcycles.setAlignment(Label.RIGHT);
                 lblcycles.setBounds(102, 58, 83, 23);
                 panelDoG.add(lblcycles);
                 
                 tfcycles = new JTextField("1", 10);
                 tfcycles.setBounds(201, 58, 52, 21);
                 panelDoG.add(tfcycles);
                 tfcycles.setEditable(true);
                 
                 labelFFT = new Label("Fourier");
                 labelFFT.setBounds(11, 86, 86, 22);
                 panelDoG.add(labelFFT);
                 labelFFT.setFont(new Font("Arial", Font.BOLD, 12));
                 
                 checkBoxFourier = new JCheckBox("Include");
                 checkBoxFourier.setBounds(7, 114, 86, 23);
                 panelDoG.add(checkBoxFourier);
                 
                 button = new JButton("Apply");
                 button.setBounds(8, 141, 89, 23);
                 panelDoG.add(button);
                 
                 label_5 = new Label("DoG cycles");
                 label_5.setBounds(119, 141, 67, 23);
                 panelDoG.add(label_5);
                 label_5.setAlignment(Label.RIGHT);
                 
                 label_4 = new Label("blur accuracy");
                 label_4.setBounds(107, 113, 79, 22);
                 panelDoG.add(label_4);
                 label_4.setAlignment(Label.RIGHT);
                 
                 label_3 = new Label("sigma");
                 label_3.setBounds(146, 86, 40, 22);
                 panelDoG.add(label_3);
                 label_3.setAlignment(Label.RIGHT);
                 
                 textField = new JTextField("1", 10);
                 textField.setBounds(202, 86, 52, 21);
                 panelDoG.add(textField);
                 textField.setEditable(true);
                 
                 textField_1 = new JTextField("0.0002", 10);
                 textField_1.setBounds(202, 113, 52, 22);
                 panelDoG.add(textField_1);
                 textField_1.setEditable(true);
                 
                 textField_2 = new JTextField("1", 10);
                 textField_2.setBounds(202, 141, 52, 21);
                 panelDoG.add(textField_2);
                 textField_2.setEditable(true);
                 
                 labelLoG = new Label("LoG");
                 labelLoG.setBounds(13, 168, 86, 22);
                 panelDoG.add(labelLoG);
                 labelLoG.setFont(new Font("Arial", Font.BOLD, 12));
                 
                 checkBoxLoG = new JCheckBox("Include");
                 checkBoxLoG.setBounds(9, 196, 86, 23);
                 panelDoG.add(checkBoxLoG);
                 
                 button_1 = new JButton("Apply");
                 button_1.setBounds(10, 223, 89, 23);
                 panelDoG.add(button_1);
                 
                 label_7 = new Label("sigma");
                 label_7.setBounds(148, 168, 40, 22);
                 panelDoG.add(label_7);
                 label_7.setAlignment(Label.RIGHT);
                 
                 label_8 = new Label("blur accuracy");
                 label_8.setBounds(109, 195, 79, 22);
                 panelDoG.add(label_8);
                 label_8.setAlignment(Label.RIGHT);
                 
                 label_9 = new Label("DoG cycles");
                 label_9.setBounds(121, 223, 67, 23);
                 panelDoG.add(label_9);
                 label_9.setAlignment(Label.RIGHT);
                 
                 textField_3 = new JTextField("1", 10);
                 textField_3.setBounds(204, 168, 52, 21);
                 panelDoG.add(textField_3);
                 textField_3.setEditable(true);
                 
                 textField_4 = new JTextField("0.0002", 10);
                 textField_4.setBounds(204, 195, 52, 22);
                 panelDoG.add(textField_4);
                 textField_4.setEditable(true);
                 
                 textField_5 = new JTextField("1", 10);
                 textField_5.setBounds(204, 223, 52, 21);
                 panelDoG.add(textField_5);
                 textField_5.setEditable(true);
                 
                 separator = new JSeparator();
                 separator.setForeground(Color.BLACK);
                 separator.setBounds(7, 84, 248, 2);
                 panelDoG.add(separator);
                 
                 separator_1 = new JSeparator();
                 separator_1.setForeground(Color.BLACK);
                 separator_1.setBounds(8, 165, 248, 2);
                 panelDoG.add(separator_1);
                 
                 panelDotCount = new JPanel();
                 panelDotCount.setLayout(null);
                 panelDotCount.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                 panelDotCount.setBackground(SystemColor.menu);
                 panelDotCount.setBounds(11, 357, 263, 157);
                 getContentPane().add(panelDotCount);
                 
                 labelDotCount = new Label("Dot count");
                 labelDotCount.setFont(new Font("Arial", Font.BOLD, 12));
                 labelDotCount.setBounds(10, 10, 68, 22);
                 panelDotCount.add(labelDotCount);
                 
                 btnDotCount = new JButton("Apply");
                 btnDotCount.setBounds(5, 128, 89, 23);
                 panelDotCount.add(btnDotCount);
                 btnDotCount.addActionListener(new CountDots_A());
                 
                 checkBoxPlotDotCount = new JCheckBox("Plot");
                 checkBoxPlotDotCount.setSelected(true);
                 checkBoxPlotDotCount.setBounds(6, 38, 86, 23);
                 panelDotCount.add(checkBoxPlotDotCount);
                 
                 lbltolerance2 = new Label("tolerance");
                 lbltolerance2.setAlignment(Label.RIGHT);
                 lbltolerance2.setBounds(114, 10, 71, 22);
                 panelDotCount.add(lbltolerance2);
                 
                 tftolerance2 = new JTextField("100", 10);
                 tftolerance2.setBounds(218, 10, 35, 20);
                 panelDotCount.add(tftolerance2);
                 tftolerance2.setEditable(true);
                 
                 lblreframe = new Label("reference frame");
                 lblreframe.setAlignment(Label.RIGHT);
                 lblreframe.setBounds(80, 38, 105, 22);
                 panelDotCount.add(lblreframe);
                 
                 tfreframe = new JTextField("2", 10);
                 tfreframe.setBounds(218, 39, 35, 20);
                 panelDotCount.add(tfreframe);
                 tfreframe.setEditable(true);
                 

                 lbllifetime = new Label("lifetime");
                 lbllifetime.setAlignment(Label.RIGHT);
                 lbllifetime.setBounds(126, 65, 59, 22);
                 panelDotCount.add(lbllifetime);
                 
                 tflifetime = new JTextField("4", 10);
                 tflifetime.setBounds(218, 66, 35, 20);
                 panelDotCount.add(tflifetime);
                 tflifetime.setEditable(true);
                 
                 JCheckBox checkBoxCorrLifetimeDotCount = new JCheckBox("Lifetime corr");
                 checkBoxCorrLifetimeDotCount.setBounds(140, 94, 113, 23);
                 panelDotCount.add(checkBoxCorrLifetimeDotCount);
                 
                 checkBoxBordersDotCount = new JCheckBox("Border corr");
                 checkBoxBordersDotCount.setSelected(true);
                 checkBoxBordersDotCount.setBounds(6, 64, 114, 23);
                 panelDotCount.add(checkBoxBordersDotCount);
                 
                 panelProgress = new JPanel();
                 panelProgress.setLayout(null);
                 panelProgress.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                 panelProgress.setBackground(SystemColor.menu);
                 panelProgress.setBounds(10, 516, 263, 100);
                 getContentPane().add(panelProgress);
                 
                 labelProgress = new Label("Progress");
                 labelProgress.setFont(new Font("Arial", Font.BOLD, 12));
                 labelProgress.setBounds(10, 10, 67, 22);
                 panelProgress.add(labelProgress);
                 
                 lblframe = new Label("processing frame");
                 lblframe.setAlignment(Label.RIGHT);
                 lblframe.setBounds(83, 10, 102, 22);
                 panelProgress.add(lblframe);
                 
                 tfframe = new JTextField("1", 10);
                 tfframe.setBounds(218, 10, 35, 20);
                 panelProgress.add(tfframe);
                 tfframe.setEditable(false);
                 
                 lblelapsedtime = new Label("elapsed time");
                 lblelapsedtime.setAlignment(Label.RIGHT);
                 lblelapsedtime.setBounds(106, 38, 79, 22);
                 panelProgress.add(lblelapsedtime);
                 
                 tfelapsedtime = new JTextField("0", 10);
                 tfelapsedtime.setBounds(218, 40, 35, 20);
                 panelProgress.add(tfelapsedtime);
                 tfelapsedtime.setPreferredSize(new Dimension(26, 20));
                 tfelapsedtime.setEditable(false);
                 
//      btnDetect = new JButton("Detect");   // construct the Button component
//      add(btnDetect);
//      // "super" Frame adds Button
//      btnPlot = new Button("Plot");   // construct the Button component
//      add(btnPlot);                    // "super" Frame adds Button
                      
//      final double gbSigma1=Double.parseDouble(tfgbSigma1.getText());
//	  double accuracy = Double.parseDouble(tfaccuracy.getText());//0.0002; // accuracy for Gaussian blur, pretty standard
//	  int cycles = Integer.parseInt(tfcycles.getText());// 1; //DoG cycles
                      
//      btnDetect.addActionListener(this);
                      JButton btnDetect = new JButton("Detect All");
                      btnDetect.setBounds(10, 38, 89, 25);
                      panelProgress.add(btnDetect);
                      btnDetect.setPreferredSize(new Dimension(65, 25));
                      
                      JButton btnPlot = new JButton("Plot");
                      btnPlot.setLocation(164, 71);
                      panelProgress.add(btnPlot);
                      btnPlot.setPreferredSize(new Dimension(65, 25));
                      btnPlot.setSize(new Dimension(89, 25));
                      
                      table = new JTable();
                      table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                      table.setBounds(283, 11, 532, 76);
                      getContentPane().add(table);
                      
                      progressBar = new JProgressBar();
                      progressBar.setBounds(10, 619, 267, 14);
//                      getContentPane().add(progressBar);
                      getContentPane().add(progressBar);
                      progressBar.setValue(0);
                      progressBar.setStringPainted(true);
                      
                      Canvas canvasDdt = new Canvas();
                      canvasDdt.setForeground(Color.WHITE);
                      canvasDdt.setBackground(Color.WHITE);
                      canvasDdt.setBounds(288, 330, 527, 210);
                      getContentPane().add(canvasDdt);
                      
                      btnPlot.addActionListener(new PlotDotResults_A());
                      btnDetect.addActionListener(this);
                      
                     
//       
//       JButton btnDoGonly = new JButton("only DoG");
//       add (btnDoGonly);
//       btnDoGonly.putClientProperty("gbSigma1", gbSigma1);
//       btnDoGonly.putClientProperty("accuracy", accuracy);
//       btnDoGonly.putClientProperty("cycles", cycles);
//       btnDoGonly.addActionListener(new DoGOfImage()); 
//       
//      JButton btnDetect = new JButton(new AbstractAction("Detect") {
       
       
            
      
//      btnPlot.addActionListener(this);
      
         // btnCount is the source object that fires ActionEvent when clicked.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Clicking btnCount invokes actionPerformed().
 
      setTitle("Dot Detector");  // "super" Frame sets its title
      setSize(841, 675);        // "super" Frame sets its initial window size
 
      // For inspecting the components/container objects
      // System.out.println(this);
      // System.out.println(lblCount);
      // System.out.println(tfCount);
      // System.out.println(btnCount);
 
      setVisible(true);         // "super" Frame shows
 
      // System.out.println(this);
      // System.out.println(lblCount);
      // System.out.println(tfCount);
      // System.out.println(btnCount);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
 
   // The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor to setup the GUI, by allocating an instance
	   new DetectDots_A();
      
   // start ImageJ
    new ImageJ();
   	ImagePlus imageA = IJ.openImage();
   	imageA.show();
//   	ImageCanvas imageAC=imageA.getCanvas();
   	
//   	DetectDots_A.EPanel_1.setBackground(Color.BLUE);

   	
   	
   	
    //Graphics g = DetectDots_A.panelGFX.getGraphics();
//    g.setColor(new Color(255, 0, 0));
//    g.drawString("Hello", 200, 200);
////	DetectDots_A.canvas.getGraphics().drawImage(imageA.getBufferedImage(), 0, 0, null)
	
//	canvas.getGraphics().drawImage(img, 0,0, null);
//   	ImagePlus imageA = WindowManager.getCurrentImage();
//      	
      
//    final DetectDots_A it = new DetectDots_A();
//
//    JFrame frame = new JFrame("Progress Bar Example");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setContentPane(it);
//    frame.pack();
//    frame.setVisible(true);
//
//    for (int i = 1; i <= 100; i++) {
//      final int percent = i;
//      DetectDots_A.updateBar(percent);
//      try {
//        SwingUtilities.invokeLater(new Runnable() {
//          public void run() {
//            DetectDots_A. .updateBar(percent);
//          }
//        });
//        java.lang.Thread.sleep(100);
//      } catch (InterruptedException e) {
        ;
      }
      
   	
   	
   	
   
 
   public static ImageStack DotDetect(ImageStack stack, double gbSigma, int cycles, double accuracy) {
		ImageStack stk = stack.duplicate();
		ImageProcessor	ip=stk.getProcessor(1);
		ImageProcessor	ip2=ip.duplicate();
		
		for (int i=1; i<=(stack.getSize()); i++) {
			for (int j=1; j<=cycles; j++) {
			ip=stk.getProcessor(i);
			ip2 = ip.duplicate();
			GaussianBlur gs = new GaussianBlur(); 
			gs.blurGaussian(ip2, gbSigma,gbSigma,accuracy);
			ImagePlus imageDP = new ImagePlus("imageDP", ip);
			ImagePlus imageGB = new ImagePlus("imageGB", ip2);
			ImageCalculator ik = new ImageCalculator(); 
			ik.run("OR", imageDP, imageGB);
			ik.run("Subtract", imageDP, imageGB);
			IJ.run(imageDP, "Multiply...", "value=2");
			stk.setProcessor(ip, i);
		}
		}
	return stk;
	}

	public static ImageStack SegmentDetect(ImageStack stack, double gbSigma, double tolerance, int noise, double accuracy) {
		ImageStack stk = stack.duplicate();
		ImageStack stk2=stack.duplicate();
		ImageProcessor	ip=stk.getProcessor(1);
		ImageProcessor	ip2=ip.duplicate();
		int width = ip.getWidth();
		int height = ip.getHeight();
		int newwidth=width+32;
		int newheight=height+32;
//		progressBar.setMinimum(1);
//	    progressBar.setMaximum(stack.getSize());
	    int Percent=0;
	    for (int i=1; i<=(stack.getSize()); i++) {
			ip=stk.getProcessor(i);
			ip.invert();
			CanvasResizer crz= new CanvasResizer();
			ip=crz.expandImage(ip, newwidth, newheight, 16, 16); 	
			GaussianBlur gs = new GaussianBlur(); 
			gs.blurGaussian(ip, gbSigma,gbSigma,accuracy);
			MaximumFinder mf = new MaximumFinder();
			ByteProcessor bSG= mf.findMaxima(ip, tolerance, 2, true);
			ImagePlus imageSG = new ImagePlus("imageSG", bSG);
//			imageSG.show();
			ip2=bSG;
			ip2.invert();
			ip.invert();
			BackgroundSubtracter bs = new BackgroundSubtracter();
			bs.rollingBallBackground(ip, noise, false, true, false, false, false);
			ImagePlus imageGB = new ImagePlus("imageGB", ip);
			ImageCalculator ik = new ImageCalculator();
			ik.run("OR", imageSG, imageGB);
			ip2.invert();
			ImagePlus imageRFb=CreateBinary(imageSG,true);
			ip2=imageRFb.getProcessor();
			//two options here: either to invert both ip and ip2 and calc OR => gives a nice pic
			// or do not invert and just subtract: gives a better segmentation but without the islet border
			CanvasResizer crz2= new CanvasResizer();
			ip2=crz2.expandImage(ip2, width, height, -16, -16); 	
			stk2.setProcessor(ip2, i);
			System.out.println("i="+i); // print i
			tfframe.setText(String.valueOf(i));
//			progressBar.setValue(i);
//			Percent = i;
//			DetectDots_A.updateBar(Percent);
		}
		return stk2;
	}

	public static ImageStack SegmentDetectPub(ImageStack stack, double gbSigma, int noise, double accuracy) {
		ImageStack stk = stack.duplicate();
		ImageStack stk2=stack.duplicate();
		ImageProcessor	ip=stk.getProcessor(1);
		ImageProcessor	ip2=ip.duplicate();
		int width = ip.getWidth();
		int height = ip.getHeight();
		int newwidth=width+32;
		int newheight=height+32;
		for (int i=1; i<=(stack.getSize()); i++) {
			ip=stk.getProcessor(i);
			ip.invert();
			CanvasResizer crz= new CanvasResizer();
			ip=crz.expandImage(ip, newwidth, newheight, 16, 16); 	
			GaussianBlur gs = new GaussianBlur(); 
			gs.blurGaussian(ip, gbSigma,gbSigma,accuracy);
			ImagePlus imageGB = new ImagePlus("imageGB", ip);
			MaximumFinder mf = new MaximumFinder();
			ByteProcessor bSG= mf.findMaxima(ip, 1, 2, true);
			ImagePlus imageSG = new ImagePlus("imageGB", bSG);
			imageSG.setTitle("imageSG");
			ip2=bSG;
			ip2.invert();
			ip.invert();
			ImageCalculator ik = new ImageCalculator();
			ik.run("OR", imageSG, imageGB);
			ip2.invert();
			CanvasResizer crz2= new CanvasResizer();
			ip2=crz2.expandImage(ip2, width, height, -16, -16); 	
			stk2.setProcessor(ip2, i);
			System.out.println("i="+i); // print i
		}
		return stk2;
	}

	public static ImageStack ConvertToDots(ImageStack stack, double tolerance) {
		ImageStack stk = stack.duplicate();
		ImageProcessor	ip=stk.getProcessor(1);
		for (int i=1; i<=(stack.getSize()); i++) {
			ip=stk.getProcessor(i);
			MaximumFinder mf = new MaximumFinder();
			ByteProcessor bSG= mf.findMaxima(ip, tolerance, 0, true);
			ip=bSG;
			stk.setProcessor(ip, i);
		}
	return stk;
	}

	public static ImageStack CorrectDots(ImageStack stack, double tolerance, double lifetime) {
		ImageStack stk = stack.duplicate();
		ImageProcessor	ip=stk.getProcessor(1);
		ImageProcessor	ipi=stk.getProcessor(2);
		int width=ip.getWidth();
		int height=ip.getHeight();
		int pival;
		for (int i=2; i<=(stack.getSize()); i++) {
			ip=stk.getProcessor(i);
			ipi=stk.getProcessor(i-1);
			for (int j=1; j<width;j++){
				for (int z=1; z<height;z++){
				pival = ip.get(j,z)+ipi.get(j,z);
				ipi.set(j, z, pival);  
				}
			}
//			ImagePlus imageSM = new ImagePlus("sum",ipi);
//			imageSM.show();
			MaximumFinder mf = new MaximumFinder();
			ByteProcessor bSG= mf.findMaxima(ipi, tolerance, 0, true);
			ipi=bSG;
			stk.setProcessor(ipi, i);
//			System.out.println("i=="+i);
		}
		return stk;
	}



	public static ImagePlus CreateBinary(ImagePlus img, boolean scale) {
//	    log.log(Level.FINEST, "Creating Binary Image");
	    BinaryProcessor proc = new BinaryProcessor(new ByteProcessor(img.getImage()));
	    proc.autoThreshold();
//	       log.log(Level.FINEST, "Created Binary Image");
	    return new ImagePlus(img.getTitle(), proc);
	} //createBinary 

	
	public static void PlotDotResults() {
		
//		int [][] M = null;
//		ImagePlus imagePL = WindowManager.getImage("DotCorrected");
		ImagePlus imagePL = WindowManager.getImage("Dotted");  
		RoiManager manager = RoiManager.getInstance();
		ResultsTable rt = manager.multiMeasure(imagePL);
		rt.show("Results"); 
		ImageProcessor ip = rt.getTableAsImage();
		ImageProcessor ip2=ip.rotateLeft();
		
//		ImageProcessor ip2=ip.duplicate();
		int width=ip.getWidth();
//		int nwidth=width*20;
		int height=ip.getHeight();
//		int nheight=(int)Math.round(height);
//		
		
//		ip2.setInterpolationMethod(1);
//		ip2.resize(nwidth, nheight, true);
//		ip2.scale(20, 2);
//		CanvasResizer crz= new CanvasResizer();
//		ip2=crz.expandImage(ip2, nwidth, nheight, 20,0); 
		System.out.println("width = "+width+"; height =  "+height+";");
		ImagePlus imageRE = new ImagePlus("Results HeatMap",ip2);
		imageRE.show();
//		imageRE.getWindow().setLocationAndSize(HeatMapPanel.getX(), HeatMapPanel.getY(), HeatMapPanel.getWidth(), HeatMapPanel.getHeight());
		imageRE.getWindow().setLocation(HeatMapPanel.getX(), HeatMapPanel.getY());
		imageRE.getWindow().setPreferredSize(HeatMapPanel.getPreferredSize());
		ImageCanvas imageREC=imageRE.getCanvas();
//		imageREC.setPreferredSize(HeatMapPanel.getSize());
//		HeatMapPanel.add(imageREC,0,0);
//		
		IJ.run("LUT... ", "open=C:\\Users\\atarasov\\Documents\\MEGAsync\\Eclipse_workspace\\royal.lut");
//		IJ.run("LUT... ", "open=C:\\royal.lut");
		IJ.run("Size...", "width=600 height=200 interpolation=None");
		ImageConverter ic = new ImageConverter(imageRE);
		ic.convertToGray8();
		//		IJ.run("3-3-2 RGB");
//		ip2.setLut(lut);		
		
		ImageProcessor ip3=ip2.duplicate();
//		int width=ip2.getWidth();
//		int height=ip2.getHeight();
		 double[][] M = new double[height][width]; 
		 double[] Mav = new double [height];
		 double[] Mav_x = new double [height];
		 double[] dMav = new double [height-1];
		 double[] dt = new double [height-1];
		 
		 for (int i = 1;i<height;i++){
//	        Mav[i]= 0;
	        Mav_x[i]=i;
	        //	        M[i]=rt.getColumnAsDoubles(i);
			 for (int j = 1; j < width; j++){
				 M[i][j]=rt.getValueAsDouble(j,i);
//	        	M [i][j] = ip2.get(i,j);
	        	Mav[i]+=M[i][j];
//	        	ip3.set(i, j,(256-M[i][j]));
	        }
		 }
		 
		 for (int i = 1;i<(height-1);i++){
		        dt[i]=i;
				dMav[i]=Mav[i+1]-Mav[i]; 
			 }
			
		 
		 
		 Plot pl = new Plot("dots","time","F", Mav_x,Mav);
//		 pl.show();
		 
		 
		 		 
		 Plot dpl = new Plot("derivative","time","dFdt", dt,dMav);
//		 dpl.show();
		 
		 ImagePlus plIP=pl.getImagePlus();
		 plIP.show();
		 plIP.getWindow().setLocationAndSize(DotsPanel.getX(), DotsPanel.getY(), DotsPanel.getWidth(), DotsPanel.getHeight());
		 DotsPanel.add(plIP.getCanvas(), 0,0);
//		 plIP.close();
		 
		 ImagePlus dplIP=dpl.getImagePlus();
		 dplIP.show();
		 dplIP.getWindow().setLocationAndSize(DotsDtPanel.getX(), DotsDtPanel.getY(), DotsDtPanel.getWidth(), DotsDtPanel.getHeight());
		 DotsDtPanel.add(dplIP.getCanvas(), 0,0);
//		 dplIP.close();
		 
		 System.out.println(Mav[22]);
	        System.out.println(M[22][35]);
	        

	}

	
	
	
   // ActionEvent handler - Called back upon button-click.
   @Override
   public void actionPerformed(ActionEvent evt) {

	   double gbSigma1=Double.parseDouble(tfgbSigma1.getText());
	   double accuracy = Double.parseDouble(tfaccuracy.getText());//0.0002; // accuracy for Gaussian blur, pretty standard
     	int cycles = Integer.parseInt(tfcycles.getText());// 1; //DoG cycles
     	int noise = Integer.parseInt(tfnoise.getText()); //25; // for segmentation
     	double gbSigma2 = Double.parseDouble(tfgbSigma2.getText()); //1; //for segmentation
     	double tolerance = Double.parseDouble(tftolerance1.getText()); //10;//for segmentation
     	double tolerance2 = Double.parseDouble(tftolerance2.getText()); //100; // for dot detection
     	int refFrame = Integer.parseInt(tfreframe.getText()); //5; //frame for roi detection
     	int lifetime = Integer.parseInt(tflifetime.getText()); //4;
//     	double gbSigma3 = 4;
     	double StartTime=System.nanoTime();
     	double ElapsedTime;
//   	ImagePlus imageA = IJ.openImage();
//      	StartTime=System.nanoTime();
//      	imageA.show();
//        	ImagePlus imageAa = WindowManager.getCurrentImage();
    progressBar.setValue(0);
   	IJ.run("Duplicate...", "duplicate");
   	ImagePlus imageA = WindowManager.getCurrentImage();	
	imageA.setTitle("imageA");
	ImageCanvas imageAC = new ImageCanvas(imageA);
	DetectDots_A.canvas=imageAC;
   	
	ImageStack stackA=imageA.getImageStack();
   	ImageStack stkDP=DotDetect(stackA,gbSigma1,cycles,accuracy);
   	ImagePlus stackDP = new ImagePlus("DoG", stkDP);
    stackDP.show();

    ImageStack stkSG=SegmentDetect(stackA,gbSigma2,tolerance,noise,accuracy);
    ImagePlus stackSG = new ImagePlus("Segmented", stkSG);
    stackSG.show();
      	
//      	This is if a pretty stack is required for e.g. a presentation
//      	ImageStack stkPB = SegmentDetectPub(stackA, gbSigma2, noise, accuracy);
//      	ImagePlus stackPB = new ImagePlus("PubSegmented", stkPB);
//      	stackPB.show();
      //	
      	
    ImageCalculator ik = new ImageCalculator();
    ImagePlus stackDF = stackDP.duplicate();
    stackDF.setTitle("Difference");
    ik.run("AND stack", stackDF, stackSG);
    stackDF.show();
    
    ImageStack stkDT = ConvertToDots(stackDF.getStack(), tolerance2);
      	ImagePlus stackDT = new ImagePlus("Dotted", stkDT);
      	stackDT.show();
      	
      	ImageStack stkCDT=CorrectDots(stackDT.getStack(), tolerance2, lifetime);
      	ImagePlus stackCDT = new ImagePlus("DotCorrected", stkCDT);
      	stackCDT.show();
      	
      	ImageProcessor ip=stkSG.getProcessor(refFrame);
      	ImagePlus imageRF = new ImagePlus("Reference frame",ip);
      	ip.invert();
      	ImagePlus imageRFb=CreateBinary(imageRF,true);
//      	ip.convertToByte(true);
//      	ImagePlus imageRF = new ImagePlus("Reference for ROI",ip);
      	imageRFb.show();
      	ParticleAnalyzer pa = new ParticleAnalyzer(ParticleAnalyzer.ADD_TO_MANAGER, Measurements.AREA, null, 0, 100000000);
//      	pa = new ParticleAnalyzer(ParticleAnalyzer.SHOW_NONE, Measurements.AREA, tmpResults, 0, Double.POSITIVE_INFINITY);
      	pa.analyze(imageRFb, imageRFb.getProcessor());
      //	
      	ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
      	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
      	tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
      	progressBar.setValue(20);
      // Display the counter value on the TextField tfCount
//      tfCount.setText(count + ""); // convert int to String
      	
      
   }
}
   
class EPanel extends JPanel {

    public EPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
    }  
}
