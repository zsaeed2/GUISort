package GUI;

import java.awt.Graphics;
import java.util.Stack;
import java.applet.Applet;
import javax.swing.*;

public class mergeSort extends sortArray
{
  private JButton jButton1 = new JButton();
  private JToggleButton jToggleButton1 = new JToggleButton();
  private JTextArea jTextArea1 = new JTextArea();


   public void init()
   {
     super.init(); //initialize the applet.
   }

  //merge the two subarrays. this functions do the merge in the same array rather
  //using a temporary array.
  void merge(int first, int mid, int last)
  {
    int first1 = first;
    int last1 = mid;
    int first2 = mid+1;
    int last2 = last;

    while((first1 <= last1) && (first2 <= last2))
    {
      index = first1;
      lastSwap = first2;

      repaint();
      if(executionMode == STEP_BY_STEP)
      {
        sortingThread.suspend();
      }
      else
      {
        try
        {
          sortingThread.sleep(200);
        }catch(InterruptedException e){}
      }
      if(array[first1].getElement() > array[first2].getElement())
      {
        int temp = array[first2].getElement();
        shiftBlockRightOnePos(first1, last1);
        array[first1].setElement(temp);
        first1++;
        first2++;
        last1++;
      }
      else
      {//array[first] is smaller than array[first2]
        first1++;
      }

    }
  }

  void sort(int first, int last)//recursive merge sort
  {
    if(first < last)
    {
      int mid = (int)(first+last)/2;
      sort(first, mid);
      sort(mid+1, last);
      merge(first, mid, last); //merge the two subarrays.
    }
  }

  //shift a block inside "array" one position to the right. the element at "last+1"
  //will be overwritten.
  void shiftBlockRightOnePos(int first, int last)
  {
      while(last >= first)
      {
        array[last+1].setElement(array[last].getElement());
        last--;
      }
  }


  //sort the array in a separate thread.
  public void run()
  {
    sort(0, ARRAY_SIZE-1);
    index = -1;
    lastSwap = -1;
    createFirstStepOptions();//restore the droplist.
    repaint();
  }

  public mergeSort() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jTextArea1.setText("jTextArea1");
    jToggleButton1.setText("jToggleButton1");
    jButton1.setText("jButton1");
  }

}