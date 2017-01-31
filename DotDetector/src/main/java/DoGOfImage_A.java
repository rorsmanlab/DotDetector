import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;

	public class DoGOfImage_A { // implements ActionListener {
//
//	@Override
	public static void DoGOfImage() { //actionPerformed(ActionEvent arg0) {
		 double gbSigma1=Double.parseDouble(DetectDots_A.tfgbSigma1.getText());
		 double accuracy = Double.parseDouble(DetectDots_A.tfaccuracy.getText()); //0.0002; // accuracy for Gaussian blur, pretty standard
	     int cycles = Integer.parseInt(DetectDots_A.tfcycles.getText());// 1; //DoG cycles
	     double StartTime=System.nanoTime();
	     double ElapsedTime;

	    DetectDots_A.progressBar.setValue(0);
	    ImagePlus image0 = WindowManager.getCurrentImage();
	    DetectDots_A.RawImageName=WindowManager.getCurrentWindow().getTitle();
	   	IJ.run("Duplicate...", "duplicate");
	   	ImagePlus imageA = WindowManager.getCurrentImage();	
		imageA.setTitle("imageA");
		imageA.getWindow().setLocation(300, 0);
		
		ImageStack stackA=imageA.getImageStack();
	   	ImageStack stkDP=DetectDots_A.DotDetect(stackA,gbSigma1,cycles,accuracy);
	   	ImagePlus stackDP = new ImagePlus("DoG", stkDP);
	    stackDP.show();
	    stackDP.getWindow().setLocation(300, 0);
	    ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
      	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
      	DetectDots_A.tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
      	imageA.close();
      	System.out.println(DetectDots_A.RawImageName);
}
}
