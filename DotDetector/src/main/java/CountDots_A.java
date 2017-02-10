import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;
import ij.measure.Measurements;
import ij.measure.ResultsTable;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.ParticleAnalyzer;
import ij.plugin.frame.RoiManager;
import ij.process.ImageProcessor;

	public class CountDots_A { //implements ActionListener {

	public static void CountDots () { // actionPerformed(ActionEvent arg0) {
		double tolerance2 = Double.parseDouble(DetectDots_A.tftolerance2.getText()); //100; // for dot detection
     	int refFrame = Integer.parseInt(DetectDots_A.tfreframe.getText()); //5; //frame for roi detection
//     	int lifetime = Integer.parseInt(DetectDots_A.tflifetime.getText()); //4;
	    double StartTime=System.nanoTime();
	   	double ElapsedTime;

	    DetectDots_A.progressBar.setValue(0);

		
	    if (DetectDots_A.chckbxDeletePrevious.isSelected()) {
	    	ResetDotCount();
	    }
	    
	    ImagePlus stackDP = WindowManager.getImage("DoG");
		ImageStack stkDP = stackDP.getStack();
		ImageStack stkDF;
		ImagePlus stackSG = WindowManager.getImage("Segmented");
		ImageStack stkSG = stackSG.getImageStack();

	    if (DetectDots_A.checkBoxBordersDotCount.isSelected()){
//        ImageCalculator ik = new ImageCalculator();
//        ik.run("Subtract stack", stackDF, stackSG);
	    	 stkDF=HessianCorrect_A.HessianCorrect(stkDP);
	    	 System.out.println("applying Hessian correction");
	    	}
        else {
        	stkDF=stkDP.duplicate();
        }
	    ImagePlus stackDF = new ImagePlus("Difference",stkDF);
	    stackDF.setTitle("Difference");
            
        
        
//        stackDF.show();
//        stackDF.getWindow().setLocation(300, stackDF.getWindow().getHeight()+10);
	            
        ImageStack stkDT = DetectDots_A.ConvertToDots(stackDF.getStack(), tolerance2);
        	ImagePlus stackDT = new ImagePlus("Dotted", stkDT);
          	stackDT.show();
            stackDT.getWindow().setLocation(stackDT.getWindow().getWidth()+300, stackDT.getWindow().getHeight()+10);

//         if (DetectDots_A.checkBoxLifetimeCorr.isSelected()){
//        	 ImageStack stkCDT=DetectDots_A.CorrectDots(stackDT.getStack(), tolerance2, lifetime);
//           	ImagePlus stackCDT = new ImagePlus("DotCorrected", stkCDT);
//           	stackCDT.show();
//             stackCDT.getWindow().setLocation(300, (stackCDT.getWindow().getHeight()+10)*2);
//          };
         
          	
          	
          	ImageProcessor ipd=stkSG.getProcessor(refFrame);
          	ImageProcessor ip = ipd.duplicate();
          	ImagePlus imageRF = new ImagePlus("Reference frame",ip);
          	ip.invert();
          	ImagePlus imageRFb=DetectDots_A.CreateBinary(imageRF,true);
//          	ip.convertToByte(true);
//          	ImagePlus imageRF = new ImagePlus("Reference for ROI",ip);
          	imageRFb.show();
//          	imageRFb.getWindow().setLocation(imageRFb.getWindow().getWidth()+300, (WindowManager.getWindow("HessianCorr").getHeight()+10)*2);
//            imageRFb.getWindow().setLocation(imageRFb.getWindow().getWidth()+300, (stackDT.getWindow().getHeight()+10)*2);
          	imageRFb.getWindow().setLocation(300, (stackDT.getWindow().getHeight()+10)*1);
          	
          	ParticleAnalyzer pa = new ParticleAnalyzer(ParticleAnalyzer.ADD_TO_MANAGER, Measurements.AREA, null, 0, 100000000);
//          	pa = new ParticleAnalyzer(ParticleAnalyzer.SHOW_NONE, Measurements.AREA, tmpResults, 0, Double.POSITIVE_INFINITY);
          	pa.analyze(imageRFb, imageRFb.getProcessor());
          //	
          if (DetectDots_A.checkBoxPlotDotCount.isSelected()){
        	DetectDots_A.PlotDotResults();
        	};
        	
//        	IJ.run("Merge Channels...", "c1=DoG c2=LocalMax create keep ignore");
//        	WindowManager.getCurrentImage().setTitle("DoG_LocalMax");
        	
        	IJ.run("Merge Channels...", "c1=LocalMax c2=Dotted create keep ignore");
        	WindowManager.getCurrentImage().setTitle("Lmax_Hess");
        	WindowManager.getWindow("Lmax_Hess").setLocation(300, (WindowManager.getWindow("Lmax_Hess").getHeight()+10)*2);
        	
           	IJ.run("Merge Channels...", "c1=DoG c2=Dotted create keep ignore");
        	WindowManager.getCurrentImage().setTitle("DoG_Hess");
        	WindowManager.getWindow("DoG_Hess").setLocation(WindowManager.getWindow("DoG_Hess").getWidth()+300, (WindowManager.getWindow("DoG_Hess").getHeight()+10)*2);
        	
          	ElapsedTime=Math.round((System.nanoTime()-StartTime)*1E-07);
          	System.out.println("Elapsed time = "+ ElapsedTime/100 +" s");
          	DetectDots_A.tfelapsedtime.setText(String.valueOf(ElapsedTime/100));
}

	public static void ResetDotCount() {
		ImagePlus image0 = WindowManager.getImage(DetectDots_A.RawImageName);
		WindowManager.setCurrentWindow(image0.getWindow());
		if (WindowManager.getImage("dots")!=null){
			WindowManager.getImage("dots").changes=false;
			WindowManager.getImage("dots").close();}
		if (WindowManager.getImage("derivative")!=null) {
			WindowManager.getImage("derivative").changes=false;
			WindowManager.getImage("derivative").close();}
		if (WindowManager.getImage("Results HeatMap")!=null) {
			WindowManager.getImage("Results HeatMap").changes=false;
			WindowManager.getImage("Results HeatMap").close();}
		if (WindowManager.getImage("Dotted")!=null) {WindowManager.getImage("Dotted").close();}
		if (WindowManager.getImage("Difference")!=null) {WindowManager.getImage("Difference").close();}
		if (WindowManager.getImage("Reference frame")!=null) {WindowManager.getImage("Reference frame").close();}
		if (WindowManager.getImage("DotCorrected")!=null) {WindowManager.getImage("DotCorrected").close();}
		if (WindowManager.getImage("DoG_LocalMax")!=null) {WindowManager.getImage("DoG_LocalMax").close();}
		if (WindowManager.getImage("Lmax_Hess")!=null) {WindowManager.getImage("Lmax_Hess").close();}
		if (WindowManager.getImage("DoG_Hess")!=null) {WindowManager.getImage("DoG_Hess").close();}
		if (WindowManager.getImage("LocalMax")!=null) {WindowManager.getImage("LocalMax").close();}
		if (WindowManager.getImage("HessianCorr")!=null) {WindowManager.getImage("HessianCorr").close();}
		if (RoiManager.getInstance()!=null) {RoiManager.getInstance().close();}
		if (ResultsTable.getResultsWindow()!=null) {ResultsTable.getResultsWindow().close(false);}
		if (WindowManager.getImage("imageDP")!=null) {WindowManager.getImage("imageDP").setTitle("DoG");}

	}
}
