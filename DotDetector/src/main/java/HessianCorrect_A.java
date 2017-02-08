import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;
import ij.process.ImageProcessor;

public class HessianCorrect_A {

	public static ImageStack HessianCorrect(ImageStack stkDP) {
		ImagePlus stackDP = WindowManager.getImage("imageDP");
//		ImagePlus stackDPdelta = stackDP.duplicate();
//		ImagePlus stackDPdeltaH = stackDP.duplicate();
//		ImageStack stkDPdelta = stackDP.getStack().duplicate();
//		ImageStack stkDPdeltaH = stackDP.getStack().duplicate();
		ImageStack stkDPdeltaH = stkDP.duplicate();
		ImageProcessor ip = stkDPdeltaH.getProcessor(1);
//		Rectangle rangle9 = new Rectangle (1, 1, 3, 3);
//		Roi roi9 = new Roi(rangle9);
		int height = ip.getHeight();
		int width = ip.getWidth();
		ip.setRoi(1, 1, 4, 4);
		ImageProcessor dip=ip.crop();
		double ans;
		int a_max=Integer.parseInt(DetectDots_A.tfHessian_a.getText());
		double max = dip.get(1);
		double min = dip.get(1);
//		System.out.println("height = "+height);

		
		for (int z=1; z<=stkDPdeltaH.getSize();z++){
			ip=stkDPdeltaH.getProcessor(z);
			for (int x=2; x<width-1;x++){
				for (int y=2; y<height-1;y++) {
					ip.setRoi(x-1, y-1, 3, 3);
					dip=ip.crop();
					max=dip.getMax();
			   	    min=dip.getMin();
//			   	    System.out.println("max="+max+"; Min= "+min);
//			   	    System.out.print("pixel value = "+dip.getPixelValue(2,3));
			   	     if(max==dip.getPixelValue(2,2)&&min!=max){
			    	   ip.set(x, y, ip.get(x, y));
			    	  
//			    	   System.out.println(dip.getHeight()+" "+dip.getWidth());
//			    	   System.out.println(dip);
//			    	   System.out.print(dip.getPixelValue(2,3));
			    	   ans=ComputeA(dip);
			    	   if(ans<a_max&&ans>0){
			    		   ip.set(x, y, ip.get(x, y));}
			    	   else {
			    		   ip.set(x, y, 0);}
			    	   }
			       else {
//			    	   System.out.println("zeroing");
			    		ip.set(x, y, 0);	   
			    	   };
//			    		System.out.println("y="+y);
				}
//				System.out.println("x="+x);
			}
		}
//		ImagePlus stackDPdeltaH = new ImagePlus("HessianCorr",stkDPdeltaH);
//		stackDPdeltaH.show();
		return stkDPdeltaH;
}

	private static double ComputeA(ImageProcessor dip) {
		double Dxx=dip.getPixelValue(1,2)-2*dip.getPixelValue(2,2)+dip.getPixelValue(3,2);
		double Dyy=dip.getPixelValue(2,1)-2*dip.getPixelValue(2,2)+dip.getPixelValue(2,3);
		double Dxy=(dip.getPixelValue(3,3)+dip.getPixelValue(1,1)-dip.getPixelValue(3,3)-dip.getPixelValue(1,3))/4;
		double a=Math.pow((Dxx+Dyy),2)/(Dxx*Dyy-Dxy*Dxy);   
//		double a=dip.getPixelValue(2, 3);
//		System.out.println("here is a "+a);
		 return a;
	}
}