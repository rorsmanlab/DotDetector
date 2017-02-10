import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;
import ij.plugin.filter.MaximumFinder;
import ij.process.ByteProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

public class HessianCorrect_A {

	public static ImageStack HessianCorrect(ImageStack stkDP) {

		double tolerance2 = Double.parseDouble(DetectDots_A.tftolerance2.getText()); //100; // for dot detection
		double thresholdHess = Double.parseDouble(DetectDots_A.tfThreshold.getText());
		ImageStack stkDPdeltaH = stkDP.duplicate();
//		ImageStack stkDPdelta = stkDP.duplicate();
		ImageProcessor ip = stkDPdeltaH.getProcessor(1);
		ImageProcessor ipD;
		int height = ip.getHeight();
		int width = ip.getWidth();
		int slices = stkDPdeltaH.getSize();
		double a, max, min;
		double rho_max=Double.parseDouble(DetectDots_A.tfHessian_rho.getText());
		double a_max=Math.pow((rho_max+1),2)/rho_max;
		ip.setRoi(0, 0, 3, 3);
		ImageProcessor dip=ip.crop();
//		FloatProcessor fdip;
		
		ImageStack stkDPdelta = DetectDots_A.ConvertToDots(stkDP, tolerance2);
		ImagePlus stackDPdelta = new ImagePlus("LocalMax",stkDPdelta);
		stackDPdelta.show();
		stackDPdelta.getWindow().setLocation(stackDPdelta.getWindow().getWidth()+300, stackDPdelta.getWindow().getHeight()+10);

		ImageProcessor dipD;
		
		for (int z=1; z<=slices;z++){
			ip=stkDPdeltaH.getProcessor(z);
			ipD=stkDPdelta.getProcessor(z);

			for (int x=1; x<width-1;x++){
				for (int y=1; y<height-1;y++) {
					ip.setRoi(x-1, y-1, 3, 3);
					dip=ip.crop();
//					ipD.setRoi(x-1, y-1, 3, 3);
//					dipD=ipD.crop();
//					max=dipD.getMax();
//			   	    min=dipD.getMin();
//			   	    if(max!=dipD.getPixelValue(1,1)||min==max){
//			    	   ip.set(x, y, 0);}
					if(ip.getPixelValue(x,y)<=thresholdHess){	}
					else {
			   	    	a=ComputeA(dip);
			   	    	if(a>a_max||a<0){
//			    		   System.out.println("local max at x= "+x+", y = "+y+" rejected by Hessian");
			    		 ip.set(x, y, 0);}
			    	   else {
//			    		   System.out.println("local max at x= "+x+", y = "+y+" approved by Hessian");
//			    		   ip.set(x, y, 255);
			    		   System.out.println("a computed as "+a+"a_max = "+a_max);
			    	   }
			    	   }
			       
			   	   
			    	}
				}
		}
		ImagePlus stackDPdeltaH = new ImagePlus("HessianCorr",stkDPdeltaH);
		stackDPdeltaH.show();
		stackDPdeltaH.getWindow().setLocation(300, stackDPdeltaH.getWindow().getHeight()+10);



		return stkDPdeltaH;
}

	private static double ComputeA(ImageProcessor dip) {
		double Dxx=dip.getPixelValue(0,1)-2*dip.getPixelValue(1,1)+dip.getPixelValue(2,1);
		double Dyy=dip.getPixelValue(1,0)-2*dip.getPixelValue(1,1)+dip.getPixelValue(1,2);
		double Dxy=(dip.getPixelValue(2,2)+dip.getPixelValue(0,0)-dip.getPixelValue(2,0)-dip.getPixelValue(0,2))/4;
		double a=Math.pow((Dxx+Dyy),2)/(Dxx*Dyy-Dxy*Dxy);   
//		double a=dip.getPixelValue(2, 3);
//		System.out.println("Dxx = "+Dxx+"; Dxy = "+Dxy+"; Dyy = "+Dyy);
		 return a;
	}

	public static void HessianTest() {
		ImagePlus stackDP = WindowManager.getImage("DoG");
		HessianCorrect_A.HessianCorrect(stackDP.getStack());
		
	}
}