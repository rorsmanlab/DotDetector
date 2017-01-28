import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;

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
	    ImagePlus image0 = WindowManager.getCurrentImage();
	    DetectDots_A.RawImageName=WindowManager.getCurrentWindow().getTitle();
	   	IJ.run("Duplicate...", "duplicate");
	   	ImagePlus imageA = WindowManager.getCurrentImage();	
		imageA.setTitle("imageA");
	   	
		ImageStack stackA=imageA.getImageStack();
	   	ImageStack stkDP=DetectDots_A.DotDetect(stackA,gbSigma1,cycles,accuracy);
	   	ImagePlus stackDP = new ImagePlus("DoG", stkDP);
	    stackDP.show();
	    stackDP.getWindow().setLocation(590, 0);
	
//	    stackDP.getWindow().setLocation(imageA.getWindow().getX()-2*(stackDP.getWindow().getWidth()-16), imageA.getWindow().getY());
//	    Dimension windowSize=Dimension(DetectDots_A.getFrames().)
//	    		DetectDots_A.frame_3.getWidth();
//	    stackDP.getWindow().setLocation(DetectDots_A.getPreferredSize getContentPane(). imageA.getWindow().getX()-2*(stackDP.getWindow().getWidth()-16), imageA.getWindow().getY());
	    ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
      	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
      	DetectDots_A.tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
      	imageA.close();
//      	WindowManager.setWindow(image0.getWindow());
      	System.out.println(DetectDots_A.RawImageName);
//      	image0.getWindow().Act setActivated(); // getWindow().transferFocus();
}
}
//C:/Users/atarasov/.m2/repository/net/imagej/ij/1.49q/ij-1.49q-sources.jar