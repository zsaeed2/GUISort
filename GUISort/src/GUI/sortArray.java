package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.applet.Applet;

public class sortArray extends Applet implements Runnable, ItemListener,
                                                 MouseListener
{

  //data memeber
   protected final static int ARRAY_SIZE=30;

  //private data member.
   protected int lastSwap=0; //the last swap index.
   protected int index=0; //the current position index the program is working.
   protected static Node array[];
   protected boolean executionMode=false;

   public final static boolean STEP_BY_STEP=true;
   public final static boolean CONTINUOUS=false;


   //thread to run the sorting algoritm
   Thread sortingThread=null;

	Choice menuList = new Choice();

	String selectOption ="Select a option";
	String stopOption = "Stop";
	String stepOption = "Step by Step";
	String contOption = "Continuous";
        String startOption = "Start";


   public void init()
   {
    //resize the applet to 100x200.
     resize(120,200);
     addMouseListener(this);
     add(menuList);
     menuList.addItemListener(this);

     createFirstStepOptions();
     array = new Node[50];
     int temp=40;

     for(int i=0; i<ARRAY_SIZE; i++)
     {
       //array contains elements b/w 1-50.
       array[i]=new Node(i+1,2,temp);
       temp = temp + Node.height+1;
     }
     resetArray();

   }

   public void start()
   {
     createFirstStepOptions();
   }


  public void stop()
  {
    if(sortingThread != null)
      sortingThread.stop();
   // sortingThread = null;
  }

  //display the array in the screen.
  public void paint(Graphics g)
  {
     //display horizontal bars.
     for(int i=0; i<ARRAY_SIZE; i++)
     {
       g.fillRect(array[i].getXLeftTop(), array[i].getYLeftTop(),
                  array[i].getElement()*2, Node.height);
     }

     //fill the index bar with red color.
     if(index >= 0 && lastSwap >= 0)
     {
       g.setColor(Color.red);
       g.fillRect(array[index].getXLeftTop(), array[index].getYLeftTop(),
                  array[index].getElement()*2, Node.height);
       //fill the last swaped bar with green color.
       g.setColor(Color.green);
       g.fillRect(array[lastSwap].getXLeftTop(), array[lastSwap].getYLeftTop(),
                  array[lastSwap].getElement()*2, Node.height);
     }
  }

  void resetArray()
  {
     Random numGen = new Random(19810812); //initiate a random number generator.

     //shuffle the array for sorting.
     for(int i=0; i<ARRAY_SIZE; i++)
        swap(i,numGen.nextInt(ARRAY_SIZE));//generate a number b/w 0-49.

     index=0;
     lastSwap=0;

  }

  //swap the elemets of the array in position "index1" and "index2".
  void swap(int index1, int index2)
  {
    int temp = array[index1].getElement();
    array[index1].setElement(array[index2].getElement());
    array[index2].setElement(temp);
  }


 void createFirstStepOptions()//create the first step menu.
 {
 	menuList.removeAll();
   	menuList.addItem(selectOption);
   	menuList.addItem(stepOption);
   	menuList.addItem(contOption);
 }

//drop list event handler.
 public void itemStateChanged(ItemEvent event)
 {
   if(menuList.getSelectedItem() == stepOption)//user chooses sort the array step
   {                                           //by step.
     menuList.removeAll();
     menuList.addItem(selectOption);
     menuList.addItem(stopOption);

     if(sortingThread == null || !sortingThread.isAlive())
       menuList.addItem(startOption);
     else
     {
       menuList.remove(selectOption);
       menuList.insert("Click Mouse",0);
       menuList.addItem(contOption);
     }
     executionMode = STEP_BY_STEP;
   }
   else if(menuList.getSelectedItem() == contOption)//user chooses sort the array
   {                                                //without intervention.
     executionMode = CONTINUOUS;
     menuList.removeAll();
     menuList.addItem(selectOption);
     menuList.addItem(stopOption);

     if(sortingThread == null)
       menuList.addItem(startOption);
     else
     {
       menuList.addItem(stepOption);
       sortingThread.resume();
     }
   }
   else if(menuList.getSelectedItem() == startOption)//start sorting the array.
   {
     menuList.removeAll();

     if(executionMode == STEP_BY_STEP)
     {
       menuList.addItem("Click Mouse");
       menuList.addItem(contOption);
     }
     else
     {
       menuList.addItem(selectOption);
       menuList.addItem(stepOption);
     }
     menuList.addItem(stopOption);

     //sort the array continuosly without user intervention.
     resetArray();
     sortingThread = new Thread(this);
     sortingThread.start();
  }
   else if(menuList.getSelectedItem() == stopOption)//stop the sorting process.
   {
     //kills the sorting thread
     sortingThread.stop();
     sortingThread = null;
     createFirstStepOptions();
   }
 }


 public void mouseClicked(MouseEvent e)//process the mouse click event.
 {
   if(executionMode == STEP_BY_STEP && sortingThread != null && sortingThread.isAlive())
     sortingThread.resume();
 }

 public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 public void mousePressed(MouseEvent e){}
 public void mouseReleased(MouseEvent e){}
 public void run(){}

}

