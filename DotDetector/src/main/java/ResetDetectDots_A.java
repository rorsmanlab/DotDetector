import ij.ImagePlus;
import ij.WindowManager;
import ij.measure.ResultsTable;
import ij.plugin.frame.RoiManager;

public class ResetDetectDots_A {



public static void ResetDetectDots() {
	
	
	ImagePlus image0 = WindowManager.getImage(DetectDots_A.RawImageName);
	WindowManager.setCurrentWindow(image0.getWindow());
	CountDots_A.ResetDotCount();
	if (WindowManager.getImage("DoG")!=null) {WindowManager.getImage("DoG").close();}
	if (WindowManager.getImage("Segmented")!=null) {WindowManager.getImage("Segmented").close();}

	//	int imageNumber=WindowManager.getImageCount();
//	for (int i=1; i<=imageNumber; i++){
//		if (null==WindowManager.getImage(i).getTitle()) {
//			WindowManager.getImage(i).changes=false;
//			WindowManager.getImage(i).close();
//		}
//		else if (WindowManager.getImage(i).getTitle().equals(DetectDots_A.RawImageName)){}
//		else {
//			try {
//			WindowManager.getImage(i).changes=false;
//			WindowManager.getImage(i).close();
//			}
//			catch(NullPointerException e){
//				System.out.println("privet, ja tvoja exception");
//				ResetDetectDots_A.ResetDetectDots();
//			}
//		}
//	}
//	
//	RoiManager.getInstance().close();
//	ResultsTable.getResultsWindow().close(false);
	

	

	
};
	
}
