import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;

	public class SegmentImage_A { // implements ActionListener {
//
//	@Override
	public static void SegmentImage (){ //actionPerformed(ActionEvent arg0) {
		 int noise = Integer.parseInt(DetectDots_A.tfnoise.getText()); //25; // for segmentation
	     double gbSigma2 = Double.parseDouble(DetectDots_A.tfgbSigma2.getText()); //1; //for segmentation
	     double tolerance = Double.parseDouble(DetectDots_A.tftolerance1.getText()); //10;//for segmentation
	     double accuracy = Double.parseDouble(DetectDots_A.tfaccuracy.getText()); //0.0002; // accuracy for Gaussian blur, pretty standard
	     double StartTime=System.nanoTime();
	     double ElapsedTime;

	     DetectDots_A.progressBar.setValue(0);
	   	
	   	ImagePlus image0;
	   	if (DetectDots_A.RawImageName!=null){
	   		image0 = WindowManager.getImage(DetectDots_A.RawImageName);
	   	}
	   	else{
	   		image0 = WindowManager.getCurrentImage();
	   	};
	   	WindowManager.setCurrentWindow(image0.getWindow());
	   	IJ.run("Duplicate...", "duplicate");
	   	ImagePlus imageA = WindowManager.getCurrentImage();
		imageA.setTitle("imageA");
		ImageStack stackA=imageA.getImageStack();
		
	    ImageStack stkSG=DetectDots_A.SegmentDetect(stackA,gbSigma2,tolerance,noise,accuracy);
	    ImagePlus stackSG = new ImagePlus("Segmented", stkSG);
	    stackSG.show();
	    stackSG.getWindow().setLocation(stackSG.getWindow().getWidth()+300, 0);
	    ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
      	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
      	DetectDots_A.tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
      	imageA.close();
}
}
