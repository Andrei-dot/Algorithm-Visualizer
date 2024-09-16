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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

//TODO : Transition from HBOX to Pane layout 
// FlowPane = funny shit
public class MainView extends Pane {
	
	public int arrayMax  			= 500;
	public int arraySize 			= 15;
	public int[] array 				= new int[arraySize];
	public Random rdm				= new Random();
	
	public MainView() {
		ObservableList comp 	= this.getChildren();
		
		ToggleGroup radioGroup	= new ToggleGroup();	
		RadioButton bubbleSort	= new RadioButton("Bubble Sort");
		bubbleSort.setUserData("BubbleSort");
		bubbleSort.setToggleGroup(radioGroup);
		bubbleSort.setTranslateX(850);

		Text str				= new Text("Text");
		Text output 			= new Text();
		Font stencil			= new Font("Caladea",24);	
		str.setFont(stencil);
		output.setFont(stencil);
		output.setX(500);
		output.setY(500);
		
		Rectangle[] rects		= new Rectangle[arraySize];
		for(int i = 0; i < array.length; i++) {
			array[i] = rdm.nextInt(0, arrayMax);
			
			rects[i] = new Rectangle();
			rects[i].setWidth(20);
			rects[i].setHeight(array[i]);
			rects[i].setUserData(array[i]);
			rects[i].setX(i*30);
			rects[i].setY(0);			
			
			comp.addAll(rects[i]);
						
			output.setText(Arrays.toString(array));
		}
		
		for(Rectangle r : rects) {
			r.setAccessibleText(r.getUserData().toString());
			r.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					System.out.println("[XPOS: " + r.getX() +"]" + "[USERDATA : " + r.getUserData() + "]");
				}
			});
		}

		Button btnStart 		= new Button("Start");
		btnStart.setTranslateX(600);
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(radioGroup.getSelectedToggle() != null) {
					//TODO: Using switch instead of if
					switch(radioGroup.getSelectedToggle().getUserData().toString()) {
						case "BubbleSort":
							
						default:
							break;
					}
					if(radioGroup.getSelectedToggle().getUserData().toString() == "BubbleSort") {
						int a = array.length;
						int n = rects.length;

						bubbleSort(array,a);
						// System.out.println("\nBubble Sorted array:");
						// printArray(array,n);
						output.setText(Arrays.toString(array));
						
						boolean swapped = false;
						int i,j;
				        for (i = 0; i < n - 1; i++) {
				            for (j = 0; j < n - i - 1; j++) {
								int r1 = ((Integer) rects[j].getUserData()).intValue();
								int r2 = ((Integer) rects[j+1].getUserData()).intValue();
			            		
								if (r1>r2) {
			            		// if(rects[j].getHeight() > rects[j+1].getHeight()) {
									swapRectsPos(rects[j], rects[j+1]);
			            			swapped = true;
								}
							}
							if(swapped==false)
								break;
						}
						output.setText(Arrays.toString(array));
					}
				} else {
					str.setText("Option!");
				}
			}
		});
		
		comp.addAll(/*str,*/ btnStart, bubbleSort, output);
	}
	
	
	// TODO : repaint/animation method
	//if(rects[j].getX() > rects[j+1].getX()) {

	static void swapRectsPos(Rectangle x, Rectangle y) {
		//System.out.println("First rect : " + x.getX() + " Second rect :" + y.getX());
		
		// Swapping size, not positions, so not finished
		int temp = (int) x.getX();
		x.setX(y.getX());
		y.setX(temp);
	}
	
	static void swapRectsSize(Rectangle x, Rectangle y) {
		System.out.println("First rect : " + x.getHeight() + " Second rect :" + y.getHeight());
		
		// Swapping size, not positions, so not finished
		int temp = (int) x.getHeight();
		x.setHeight(y.getHeight());
		y.setHeight(temp);
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
