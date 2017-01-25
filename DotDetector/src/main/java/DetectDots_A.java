import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.beans.PropertyChangeEvent;

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
import ij.plugin.filter.PlugInFilter;
import ij.process.BinaryProcessor;
import ij.process.ByteProcessor;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
 
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
   private JTextField tfnoise;
   private JTextField tfgbSigma2;
   private JTextField tftolerance1;// Declare a TextField component 
   private JTextField tftolerance2;
   private JTextField tfreframe;
   private JTextField tflifetime;
   public static  JTextField tfframe;
   public static JTextField tfelapsedtime;
   private JPanel panelSegmentation;
   private JPanel panelDotCount;
   private Label labelDotCount;
   private JButton btnDotCount;
   private JCheckBox checkBoxDotCount;
   private JPanel panelProgress;
   private Label labelProgress;
   private JTable table;
   private Label labelOutput;
   private Label label;
   private Label label_2;
   public static JProgressBar progressBar;
   private JPanel panelFFT;
   private Label labelFFT;
   private JButton button;
   private JCheckBox checkBox;
   private Label label_3;
   private JTextField textField;
   private Label label_4;
   private JTextField textField_1;
   private Label label_5;
   private JTextField textField_2;
   private JPanel panel_1;
   private Label labelLoG;
   private JButton button_1;
   private JCheckBox checkBox_1;
   private Label label_7;
   private JTextField textField_3;
   private Label label_8;
   private JTextField textField_4;
   private Label label_9;
   private JTextField textField_5;
   



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
      
      label_2 = new Label("dots");
      label_2.setBounds(279, 361, 35, 22);
      getContentPane().add(label_2);
      
      label = new Label("dots");
      label.setBounds(279, 270, 35, 22);
      getContentPane().add(label);
      
      labelOutput = new Label("data output");
      labelOutput.setBounds(279, 111, 79, 22);
      getContentPane().add(labelOutput);
      
      Canvas canvas = new Canvas();
      canvas.setForeground(SystemColor.window);
      canvas.setBackground(SystemColor.window);
      canvas.setBounds(288, 276, 527, 76);
      getContentPane().add(canvas);
      
      JDesktopPane desktopPane = new JDesktopPane();
      desktopPane.setBounds(91, 358, 1, 1);
      getContentPane().add(desktopPane);
      
      panelSegmentation = new JPanel();
      panelSegmentation.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      panelSegmentation.setBackground(SystemColor.menu);
      panelSegmentation.setBounds(10, 115, 263, 93);
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
            
            JCheckBox chckbxSegmentationInclude = new JCheckBox("Include");
            chckbxSegmentationInclude.setBounds(6, 38, 86, 23);
            panelSegmentation.add(chckbxSegmentationInclude);
      
      JPanel panelDoG = new JPanel();
      panelDoG.setLayout(null);
      panelDoG.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      panelDoG.setBackground(SystemColor.menu);
      panelDoG.setBounds(10, 11, 263, 93);
      getContentPane().add(panelDoG);
      
      Label labelDoG = new Label("DoG");
      labelDoG.setFont(new Font("Arial", Font.BOLD, 12));
      labelDoG.setBounds(10, 10, 86, 22);
      panelDoG.add(labelDoG);
      
      JButton btnDoGApply = new JButton("Apply");
      btnDoGApply.setBounds(7, 65, 89, 23);
      panelDoG.add(btnDoGApply);
      btnDoGApply.addActionListener(new DoGOfImage_A());
      
      
      JCheckBox checkBoxDoGInclude = new JCheckBox("Include");
      checkBoxDoGInclude.setBounds(6, 38, 86, 23);
      panelDoG.add(checkBoxDoGInclude);
      // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
      // the components from left-to-right, and flow to next row from top-to-bottom.
 
      lblgbSigma1 = new Label("sigma");
      lblgbSigma1.setAlignment(Label.RIGHT);
      lblgbSigma1.setBounds(145, 10, 40, 22);
      panelDoG.add(lblgbSigma1);
      
      tfgbSigma1 = new JTextField("1", 10);
      tfgbSigma1.setBounds(201, 10, 52, 21);
      panelDoG.add(tfgbSigma1);
      tfgbSigma1.setEditable(true);
      
           lblaccuracy = new Label("blur accuracy");
           lblaccuracy.setAlignment(Label.RIGHT);
           lblaccuracy.setBounds(106, 37, 79, 22);
           panelDoG.add(lblaccuracy);
           
           tfaccuracy = new JTextField("0.0002", 10);
           tfaccuracy.setBounds(201, 37, 52, 22);
           panelDoG.add(tfaccuracy);
           tfaccuracy.setEditable(true);
           
                 lblcycles = new Label("DoG cycles");
                 lblcycles.setAlignment(Label.RIGHT);
                 lblcycles.setBounds(102, 65, 83, 23);
                 panelDoG.add(lblcycles);
                 
                 tfcycles = new JTextField("1", 10);
                 tfcycles.setBounds(201, 65, 52, 21);
                 panelDoG.add(tfcycles);
                 tfcycles.setEditable(true);
                 
                 panelDotCount = new JPanel();
                 panelDotCount.setLayout(null);
                 panelDotCount.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                 panelDotCount.setBackground(SystemColor.menu);
                 panelDotCount.setBounds(10, 219, 263, 93);
                 getContentPane().add(panelDotCount);
                 
                 labelDotCount = new Label("Dot count");
                 labelDotCount.setFont(new Font("Arial", Font.BOLD, 12));
                 labelDotCount.setBounds(10, 10, 68, 22);
                 panelDotCount.add(labelDotCount);
                 
                 btnDotCount = new JButton("Apply");
                 btnDotCount.setBounds(7, 65, 89, 23);
                 panelDotCount.add(btnDotCount);
                 
                 checkBoxDotCount = new JCheckBox("Include");
                 checkBoxDotCount.setBounds(6, 38, 86, 23);
                 panelDotCount.add(checkBoxDotCount);
                 
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
                 
                 panelProgress = new JPanel();
                 panelProgress.setLayout(null);
                 panelProgress.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                 panelProgress.setBackground(SystemColor.menu);
                 panelProgress.setBounds(10, 323, 263, 100);
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
                      btnDetect.setBounds(7, 35, 89, 25);
                      panelProgress.add(btnDetect);
                      btnDetect.setPreferredSize(new Dimension(65, 25));
                      
                      JButton btnPlot = new JButton("Plot");
                      btnPlot.setLocation(7, 68);
                      panelProgress.add(btnPlot);
                      btnPlot.setPreferredSize(new Dimension(65, 25));
                      btnPlot.setSize(new Dimension(89, 25));
                      
                      table = new JTable();
                      table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                      table.setBounds(283, 115, 532, 149);
                      getContentPane().add(table);
                      
                      progressBar = new JProgressBar();
                      progressBar.setBounds(10, 434, 267, 14);
//                      getContentPane().add(progressBar);
                      getContentPane().add(progressBar);
                      progressBar.setValue(0);
                      progressBar.setStringPainted(true);
                      
                      Canvas canvasddt = new Canvas();
                      canvasddt.setBackground(Color.WHITE);
                      canvasddt.setBounds(282, 372, 533, 76);
                      getContentPane().add(canvasddt);
                      
                      panelFFT = new JPanel();
                      panelFFT.setLayout(null);
                      panelFFT.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                      panelFFT.setBackground(SystemColor.menu);
                      panelFFT.setBounds(279, 12, 263, 93);
                      getContentPane().add(panelFFT);
                      
                      labelFFT = new Label("Fourier");
                      labelFFT.setFont(new Font("Arial", Font.BOLD, 12));
                      labelFFT.setBounds(10, 10, 86, 22);
                      panelFFT.add(labelFFT);
                      
                      button = new JButton("Apply");
                      button.setBounds(7, 65, 89, 23);
                      panelFFT.add(button);
                      
                      checkBox = new JCheckBox("Include");
                      checkBox.setBounds(6, 38, 86, 23);
                      panelFFT.add(checkBox);
                      
                      label_3 = new Label("sigma");
                      label_3.setAlignment(Label.RIGHT);
                      label_3.setBounds(145, 10, 40, 22);
                      panelFFT.add(label_3);
                      
                      textField = new JTextField("1", 10);
                      textField.setEditable(true);
                      textField.setBounds(201, 10, 52, 21);
                      panelFFT.add(textField);
                      
                      label_4 = new Label("blur accuracy");
                      label_4.setAlignment(Label.RIGHT);
                      label_4.setBounds(106, 37, 79, 22);
                      panelFFT.add(label_4);
                      
                      textField_1 = new JTextField("0.0002", 10);
                      textField_1.setEditable(true);
                      textField_1.setBounds(201, 37, 52, 22);
                      panelFFT.add(textField_1);
                      
                      label_5 = new Label("DoG cycles");
                      label_5.setAlignment(Label.RIGHT);
                      label_5.setBounds(118, 65, 67, 23);
                      panelFFT.add(label_5);
                      
                      textField_2 = new JTextField("1", 10);
                      textField_2.setEditable(true);
                      textField_2.setBounds(201, 65, 52, 21);
                      panelFFT.add(textField_2);
                      
                      panel_1 = new JPanel();
                      panel_1.setLayout(null);
                      panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                      panel_1.setBackground(SystemColor.menu);
                      panel_1.setBounds(552, 11, 263, 93);
                      getContentPane().add(panel_1);
                      
                      labelLoG = new Label("LoG");
                      labelLoG.setFont(new Font("Arial", Font.BOLD, 12));
                      labelLoG.setBounds(10, 10, 86, 22);
                      panel_1.add(labelLoG);
                      
                      button_1 = new JButton("Apply");
                      button_1.setBounds(7, 65, 89, 23);
                      panel_1.add(button_1);
                      
                      checkBox_1 = new JCheckBox("Include");
                      checkBox_1.setBounds(6, 38, 86, 23);
                      panel_1.add(checkBox_1);
                      
                      label_7 = new Label("sigma");
                      label_7.setAlignment(Label.RIGHT);
                      label_7.setBounds(145, 10, 40, 22);
                      panel_1.add(label_7);
                      
                      textField_3 = new JTextField("1", 10);
                      textField_3.setEditable(true);
                      textField_3.setBounds(201, 10, 52, 21);
                      panel_1.add(textField_3);
                      
                      label_8 = new Label("blur accuracy");
                      label_8.setAlignment(Label.RIGHT);
                      label_8.setBounds(106, 37, 79, 22);
                      panel_1.add(label_8);
                      
                      textField_4 = new JTextField("0.0002", 10);
                      textField_4.setEditable(true);
                      textField_4.setBounds(201, 37, 52, 22);
                      panel_1.add(textField_4);
                      
                      label_9 = new Label("DoG cycles");
                      label_9.setAlignment(Label.RIGHT);
                      label_9.setBounds(118, 65, 67, 23);
                      panel_1.add(label_9);
                      
                      textField_5 = new JTextField("1", 10);
                      textField_5.setEditable(true);
                      textField_5.setBounds(201, 65, 52, 21);
                      panel_1.add(textField_5);
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
      setSize(841, 496);        // "super" Frame sets its initial window size
 
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



	private static ImagePlus CreateBinary(ImagePlus img, boolean scale) {
//	    log.log(Level.FINEST, "Creating Binary Image");
	    BinaryProcessor proc = new BinaryProcessor(new ByteProcessor(img.getImage()));
	    proc.autoThreshold();
//	       log.log(Level.FINEST, "Created Binary Image");
	    return new ImagePlus(img.getTitle(), proc);
	} //createBinary 

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
//	public JProgressBar getProgressBar() {
//		
//		return progressBar;
//	}

   
}
   

