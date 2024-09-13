package algo_vizu_javafx.views;


import java.util.Arrays;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

//TODO : Transition from HBOX to Pane layout
public class MainView extends HBox  {
	
	public int arrayMax  			= 500;
	public int arraySize 			= 15;
	public int[] array 				= new int[arraySize];
	public Random rdm				= new Random();

	public MainView(double spacing) {
		super(spacing);
		
		
		ObservableList comp 	= this.getChildren();
		
		ToggleGroup radioGroup	= new ToggleGroup();	
		RadioButton bubbleSort	= new RadioButton("Bubble Sort");
		RadioButton quickSort	= new RadioButton("Quick Sort");
		RadioButton mergeSort	= new RadioButton("Merge Sort");
		bubbleSort.setUserData("BubbleSort");
		bubbleSort.setToggleGroup(radioGroup);
		mergeSort.setUserData("MergeSort");
		mergeSort.setToggleGroup(radioGroup);
		quickSort.setUserData("QuickSort");
		quickSort.setToggleGroup(radioGroup);		

		Text str				= new Text("Text");
		Text output 			= new Text();
		Font stencil			= new Font("Caladea",24);	
		str.setFont(stencil);
		output.setFont(stencil);
		
		Rectangle[] rects		= new Rectangle[arraySize];
		for(int i = 0; i < array.length; i++) {
			array[i] = rdm.nextInt(0, arrayMax);
			
			rects[i] = new Rectangle();
			rects[i].setWidth(20);
			rects[i].setHeight(array[i]);
			
			comp.addAll(rects[i]);
			
			System.out.print(array[i] + "-");
			output.setText(Arrays.toString(array));
		}
				
		DropShadow shadow 		= new DropShadow();
		Button btnStart 		= new Button("Start");
		Tooltip tp				= new Tooltip("Cliquez pour commencer le tri.");
		tp.setShowDelay(Duration.millis(1));
		tp.setHideDelay(Duration.millis(50));
		btnStart.setTooltip(tp);
		btnStart.setOnMouseEntered(e -> btnStart.setEffect(shadow));
		btnStart.setOnMouseExited(e -> btnStart.setEffect(null));
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(radioGroup.getSelectedToggle() != null) {
					//TODO: Using switch instead of if
					if(radioGroup.getSelectedToggle().getUserData().toString() == "BubbleSort") {
						int n = array.length;
						bubbleSort(array,n);
						System.out.println("\nBubble Sorted array:");
						printArray(array,n);
						output.setText(Arrays.toString(array));
						
					}
					if(radioGroup.getSelectedToggle().getUserData().toString() == "MergeSort") {
						// Swapping sizes, not positions, solution provisoire
						System.out.println("mergeSort()");
						boolean swapped = false;
						int a,j, temp;
						for(a = 0; a < rects.length - 1; a++) {
							for(j = 0; j < rects.length - a - 1;j++) {
								if(rects[j].getHeight() > rects[j+1].getHeight()) {
									swapRects(rects[j], rects[j+1]);
									swapped= true;
								}
							}
							if(swapped==false)
								break;
						}
						output.setText(Arrays.toString(array));
					}
					if(radioGroup.getSelectedToggle().getUserData().toString() == "QuickSort") {
						System.out.println("quickSort()");
					}
				} else {
					str.setText("Option!");
				}
			}
		});

		comp.addAll(str, btnStart, bubbleSort, mergeSort, quickSort, output);
	}
	
	// TODO : repaint method?

	// For later
	static void swapRects(Rectangle x, Rectangle y) {
		System.out.println("First rect : " + x.getHeight() + " Second rect :" + y.getHeight());
		
		// Swapping size, not positions, so not finished
		int temp = (int) x.getHeight();
		x.setHeight(y.getHeight());
		y.setHeight(temp);
	}
	
	static void randomizeArray() {
		
	}
	
    static void quickSort() {
    	
    }
    
    static void mergeSort() {
    	
    }

    // NOTE: Just need to swap the rectangles as the values are swapped (rappelle toi de ce que tu as pensé connard)
	static void bubbleSort(int arr[], int n) {
		int i, j, temp;
		boolean swapped;
		for(i = 0; i < n - 1; i++) {
			swapped=false;
			for(j = 0; j < n - i - 1; j++) {
				if(arr[j] > arr[j+1]) {
					// Here we swap the rects
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					swapped=true;
				}
			}
			
			if(swapped==false)
				break;
		}
	}
	
    static void printArray(int arr[], int size) {
        int i;
        for (i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
	
	/* Récupérer toutes les familles de charactères disponible
	 for(String s : Font.getFamilies()) {
	 
		System.out.println(s);
	}*/
    
}
