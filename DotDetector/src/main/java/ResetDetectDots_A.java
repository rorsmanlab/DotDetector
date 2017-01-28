import ij.ImagePlus;
import ij.WindowManager;
import ij.measure.ResultsTable;
import ij.plugin.frame.RoiManager;

public class ResetDetectDots_A {



public static void ResetDetectDots() {
	
	
	ImagePlus image0 = WindowManager.getImage(DetectDots_A.RawImageName);
	WindowManager.setCurrentWindow(image0.getWindow());
	int imageNumber=WindowManager.getImageCount();
//	System.out.println(imageNumber);
	
	for (int i=1; i<=imageNumber; i++){
//		System.out.println(WindowManager.getImage(i).getTitle());
		if (null==WindowManager.getImage(i).getTitle()) {
			WindowManager.getImage(i).changes=false;
			WindowManager.getImage(i).close();
		}
		else if (WindowManager.getImage(i).getTitle().equals(DetectDots_A.RawImageName)){
//			System.out.println(WindowManager.getImage(i).getTitle()+" is a match");
		}
		else {
//			System.out.println(WindowManager.getImage(i).getTitle()+" doesn't match");
			try {
			WindowManager.getImage(i).changes=false;
			WindowManager.getImage(i).close();
			}
			catch(NullPointerException e){
				System.out.println("privet, ja tvoja exception");
				ResetDetectDots_A.ResetDetectDots();
			}
//			}
		}
	}
	
	RoiManager.getInstance().close();
	ResultsTable.getResultsWindow().close(false);
	

	

	
};
	
}
