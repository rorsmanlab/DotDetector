import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;
import ij.measure.Measurements;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.ParticleAnalyzer;
import ij.process.ImageProcessor;

	public class CountDots_A implements ActionListener {
//
	@Override
	public void actionPerformed(ActionEvent arg0) {
		double tolerance2 = Double.parseDouble(DetectDots_A.tftolerance2.getText()); //100; // for dot detection
     	int refFrame = Integer.parseInt(DetectDots_A.tfreframe.getText()); //5; //frame for roi detection
     	int lifetime = Integer.parseInt(DetectDots_A.tflifetime.getText()); //4;
//	    double accuracy = Double.parseDouble(DetectDots_A.tfaccuracy.getText()); //0.0002; // accuracy for Gaussian blur, pretty standard
	    double StartTime=System.nanoTime();
	   	double ElapsedTime;

	    DetectDots_A.progressBar.setValue(0);
//	   	IJ.run("Duplicate...", "duplicate");
//	    ImagePlus stackDP = WindowManager.getCurrentImage();	
		
	   	ImagePlus stackDP = WindowManager.getImage("DoG");
		stackDP.setTitle("imageDP");
//		ImageStack stkDP = stackDP.getImageStack();
		ImagePlus stackDF=stackDP.duplicate();
		
		ImagePlus stackSG = WindowManager.getImage("Segmented");
		ImageStack stkSG = stackSG.getImageStack();
//
	    if (DetectDots_A.checkBoxBordersDotCount.isSelected()){
        ImageCalculator ik = new ImageCalculator();
//      stackDF = stackDP.duplicate();
        stackDF.setTitle("Difference");
        ik.run("AND stack", stackDF, stackSG);
        stackDF.show();
        stackDF.getWindow().setLocation(590, stackDF.getWindow().getHeight()+10);
	    };
//	    stackDF.show();
	    
        ImageStack stkDT = DetectDots_A.ConvertToDots(stackDF.getStack(), tolerance2);
          	ImagePlus stackDT = new ImagePlus("Dotted", stkDT);
          	stackDT.show();
            stackDT.getWindow().setLocation(stackDT.getWindow().getWidth()+600, stackDT.getWindow().getHeight()+10);

          	
          	ImageStack stkCDT=DetectDots_A.CorrectDots(stackDT.getStack(), tolerance2, lifetime);
          	ImagePlus stackCDT = new ImagePlus("DotCorrected", stkCDT);
          	stackCDT.show();
            stackCDT.getWindow().setLocation(590, (stackCDT.getWindow().getHeight()+10)*2);

          	
          	ImageProcessor ip=stkSG.getProcessor(refFrame);
          	ImagePlus imageRF = new ImagePlus("Reference frame",ip);
          	ip.invert();
          	ImagePlus imageRFb=DetectDots_A.CreateBinary(imageRF,true);
//          	ip.convertToByte(true);
//          	ImagePlus imageRF = new ImagePlus("Reference for ROI",ip);
          	imageRFb.show();
            imageRFb.getWindow().setLocation(imageRFb.getWindow().getWidth()+600, (stackCDT.getWindow().getHeight()+10)*2);

          	ParticleAnalyzer pa = new ParticleAnalyzer(ParticleAnalyzer.ADD_TO_MANAGER, Measurements.AREA, null, 0, 100000000);
//          	pa = new ParticleAnalyzer(ParticleAnalyzer.SHOW_NONE, Measurements.AREA, tmpResults, 0, Double.POSITIVE_INFINITY);
          	pa.analyze(imageRFb, imageRFb.getProcessor());
          //	
          if (DetectDots_A.checkBoxPlotDotCount.isSelected()){
        	DetectDots_A.PlotDotResults();
        	};
        	
          	ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
          	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
          	DetectDots_A.tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
}
}
