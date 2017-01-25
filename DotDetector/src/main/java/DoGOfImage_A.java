import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//
//public class DoGOfImage_A extends DetectDots_A implements ActionListener {
	public class DoGOfImage_A implements ActionListener {
//
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 double gbSigma1=Double.parseDouble(DetectDots_A.tfgbSigma1.getText());
		 double accuracy = Double.parseDouble(DetectDots_A.tfaccuracy.getText()); //0.0002; // accuracy for Gaussian blur, pretty standard
	     int cycles = Integer.parseInt(DetectDots_A.tfcycles.getText());// 1; //DoG cycles
	     
	     	double StartTime=System.nanoTime();
	     	double ElapsedTime;
//	   	ImagePlus imageA = IJ.openImage();
//	      	StartTime=System.nanoTime();
//	      	imageA.show();
//	        	ImagePlus imageAa = WindowManager.getCurrentImage();
	     	DetectDots_A.progressBar.setValue(0);
	   	IJ.run("Duplicate...", "duplicate");
	   	ImagePlus imageA = WindowManager.getCurrentImage();	
		imageA.setTitle("imageA");
	   	
		ImageStack stackA=imageA.getImageStack();
	   	ImageStack stkDP=DetectDots_A.DotDetect(stackA,gbSigma1,cycles,accuracy);
	   	ImagePlus stackDP = new ImagePlus("DoG", stkDP);
	    stackDP.show();
	System.out.println("Sigma 1 value = "+gbSigma1);
}
}
//C:/Users/atarasov/.m2/repository/net/imagej/ij/1.49q/ij-1.49q-sources.jar