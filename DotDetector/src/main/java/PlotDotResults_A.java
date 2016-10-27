import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Hashtable;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.Plot;
import ij.gui.Roi;
import ij.measure.ResultsTable;
import ij.plugin.CanvasResizer;
import ij.plugin.filter.ScaleDialog;
import ij.plugin.frame.RoiManager;
import ij.process.FloatProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class PlotDotResults_A implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		int [][] M = null;
		ImagePlus imagePL = WindowManager.getImage("DotCorrected");  
		RoiManager manager = RoiManager.getInstance();
		ResultsTable rt = manager.multiMeasure(imagePL);
		rt.show("Results"); 
		ImageProcessor ip = rt.getTableAsImage();
		ImageProcessor ip2=ip.rotateLeft();
		
//		ImageProcessor ip2=ip.duplicate();
		int width=ip.getWidth();
//		int nwidth=width*20;
		int height=ip.getHeight();
//		int nheight=(int)Math.round(height);
//		
		
//		ip2.setInterpolationMethod(1);
//		ip2.resize(nwidth, nheight, true);
//		ip2.scale(20, 2);
//		CanvasResizer crz= new CanvasResizer();
//		ip2=crz.expandImage(ip2, nwidth, nheight, 20,0); 
		System.out.println("width = "+width+"; height =  "+height+";");
		ImagePlus imageRE = new ImagePlus("Results HeatMap",ip2);
		imageRE.show();
		IJ.run("LUT... ", "open=C:\\Users\\atarasov\\Documents\\MEGAsync\\Eclipse_workspace\\royal.lut");
//		IJ.run("LUT... ", "open=C:\\royal.lut");
		IJ.run("Size...", "width=600 height=200 interpolation=None");
		ImageConverter ic = new ImageConverter(imageRE);
		ic.convertToGray8();
		//		IJ.run("3-3-2 RGB");
//		ip2.setLut(lut);		
		
		ImageProcessor ip3=ip2.duplicate();
//		int width=ip2.getWidth();
//		int height=ip2.getHeight();
		 double[][] M = new double[height][width]; 
		 double[] Mav = new double [height];
		 double[] Mav_x = new double [height];
		 double[] dMav = new double [height-1];
		 double[] dt = new double [height-1];
		 
		 for (int i = 1;i<height;i++){
//	        Mav[i]= 0;
	        Mav_x[i]=i;
	        //	        M[i]=rt.getColumnAsDoubles(i);
			 for (int j = 1; j < width; j++){
				 M[i][j]=rt.getValueAsDouble(j,i);
//	        	M [i][j] = ip2.get(i,j);
	        	Mav[i]+=M[i][j];
//	        	ip3.set(i, j,(256-M[i][j]));
	        }
		 }
		 
		 for (int i = 1;i<(height-1);i++){
		        dt[i]=i;
				dMav[i]=Mav[i+1]-Mav[i]; 
			 }
			
		 
		 
		 Plot pl = new Plot("_","time","F", Mav_x,Mav);
		 pl.show();
		 
		 Plot dpl = new Plot("derivative","time","dFdt", dt,dMav);
		 dpl.show();
		 
		 System.out.println(Mav[22]);
	        System.out.println(M[22][35]);
	        

	}

}
