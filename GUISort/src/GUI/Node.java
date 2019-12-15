package GUI;

public class Node
{
 int element; //the element of the node in the array.
 int x; //upper right corner x coordinate.
 int y; //upper right corner y coordinate.

 public final static int height=10; //the height of node in pixel.
 public final static int width=10; //the width of node in pixel.


   public Node(int _elem, int _x, int _y)
   {
       element = _elem;
       x = _x;
       y = _y;
   }

   public Node()
   {
     element = 0;
     x = 0;
     y = 0;
   }

   //return the element of the node.
   public int getElement(){return element;} //return the element of node.

   //set the element of node.
   public void setElement(int _elem){element=_elem;}

  //set the  left top x coordinate.
   public void setXLeftTop(int _x){x = _x;}

   //return the upper right corner x coordinate.
    public int getXLeftTop(){return x;}

   //set the y left top coordinate.
    public void setYLeftTop(int _y){y = _y;}

  //return the upper right corner y coordinate.
   public int getYLeftTop(){return y;}

  //return the lower left corner x coordinate, which is the width of the recta-
  //gle that the applet display in screen.
   public int getXRightBottom(){return x+width*element;}

  //return the lower left corner y coordinate.
   public int getYRightBottom(){return y+height;}
} 