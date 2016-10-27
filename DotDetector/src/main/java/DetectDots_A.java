import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
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
   
   
   private JTextField tfgbSigma1; // Declare a TextField component 
   private JTextField tfaccuracy;
   private JTextField tfcycles;
   private JTextField tfnoise;
   private JTextField tfgbSigma2;
   private JTextField tftolerance1;// Declare a TextField component 
   private JTextField tftolerance2;
   private JTextField tfreframe;
   private JTextField tflifetime;
   public static  JTextField tfframe;
   public static JTextField tfelapsedtime;
   
//   private JButton btnDetect;   // Declare a Button component
//   private JButton btnPlot;     // Button for plotting
 
   
      
   // Constructor to setup GUI components and event handlers
   public DetectDots_A () {
      setLayout(new GridLayout(15,4));
         // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
         // the components from left-to-right, and flow to next row from top-to-bottom.
 
      lblgbSigma1 = new Label("sigma for DoG blob detection, 1");  // construct the Label component
      add(lblgbSigma1);                    // "super" Frame adds Label
      
      tfgbSigma1 = new JTextField("1", 10); // construct the TextField component
      tfgbSigma1.setEditable(true);       // set to editable
      add(tfgbSigma1);  
 
      lblaccuracy = new Label("accuracy for Gaussian blur, pretty standard");  // construct the Label component
      add(lblaccuracy); 
      
      tfaccuracy = new JTextField("0.0002", 10); // construct the TextField component
      tfaccuracy.setEditable(true);       // set to read-only
      add(tfaccuracy);  

      lblcycles = new Label("DoG cycles");  // construct the Label component
      add(lblcycles);    
      
      tfcycles = new JTextField("1", 10); // construct the TextField component
      tfcycles.setEditable(true);       // set to read-only
      add(tfcycles); 

      lblnoise = new Label("noise for segmentation");  // construct the Label component
      add(lblnoise);  
      
      tfnoise = new JTextField("25", 10); // construct the TextField component
      tfnoise.setEditable(true);       // set to read-only
      add(tfnoise);   
      
      lblgbSigma2 = new Label("sigma for segmentation, 1");  // construct the Label component
      add(lblgbSigma2);  
      
      
      tfgbSigma2 = new JTextField("1", 10); // construct the TextField component
      tfgbSigma2.setEditable(true);       // set to editable
      add(tfgbSigma2);
      
      lbltolerance1 = new Label("tolerance for segmentation, 10");  // construct the Label component
      add(lbltolerance1); 

      
      tftolerance1 = new JTextField("10", 10); // construct the TextField component
      tftolerance1.setEditable(true);       // set to editable
      add(tftolerance1);
      
      lbltolerance2 = new Label("tolerance for dot detection, 100");  // construct the Label component
      add(lbltolerance2); 
      
      tftolerance2 = new JTextField("100", 10); // construct the TextField component
      tftolerance2.setEditable(true);       // set to editable
      add(tftolerance2);
      
      lblreframe = new Label("reference frame for segmentation and ROI");  // construct the Label component
      add(lblreframe); 
      
      tfreframe = new JTextField("2", 10); // construct the TextField component
      tfreframe.setEditable(true);       // set to editable
      add(tfreframe);
      

      lbllifetime = new Label("lifetime of a dot");  // construct the Label component
      add(lbllifetime);
      
      tflifetime = new JTextField("4", 10); // construct the TextField component
      tflifetime.setEditable(true);       // set to editable
      add(tflifetime);
      
      lblframe = new Label("processing frame");  // construct the Label component
      add(lblframe);
      
      tfframe = new JTextField("1", 10); // construct the TextField component
      tfframe.setEditable(false);       // set to editable
      add(tfframe);
      
      lblelapsedtime = new Label("elapsed time");  // construct the Label component
      add(lblelapsedtime);
      
      tfelapsedtime = new JTextField("0", 10); // construct the TextField component
      tfelapsedtime.setEditable(false);       // set to editable
      add(tfelapsedtime);
 
//      btnDetect = new JButton("Detect");   // construct the Button component
//      add(btnDetect);
//      // "super" Frame adds Button
//      btnPlot = new Button("Plot");   // construct the Button component
//      add(btnPlot);                    // "super" Frame adds Button
      
//      final double gbSigma1=Double.parseDouble(tfgbSigma1.getText());
//	  double accuracy = Double.parseDouble(tfaccuracy.getText());//0.0002; // accuracy for Gaussian blur, pretty standard
//	  int cycles = Integer.parseInt(tfcycles.getText());// 1; //DoG cycles
      
//      btnDetect.addActionListener(this);
      JButton btnDetect = new JButton("Detect");
       add( btnDetect );
       btnDetect.addActionListener(this);
       
       JButton btnPlot = new JButton("Plot");
       add (btnPlot);
       btnPlot.addActionListener(new PlotDotResults_A()); 
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
      setSize(550, 300);        // "super" Frame sets its initial window size
 
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
      // Display the counter value on the TextField tfCount
//      tfCount.setText(count + ""); // convert int to String
   }
//   public void actionPerformed2(ActionEvent evt) {
//	   
//   }
   }
   

