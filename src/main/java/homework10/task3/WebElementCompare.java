package homework10.task3;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class WebElementCompare {
    public static void compareElementsY(WebElement element1, WebElement element2) {
        int element1PosY = element1.getLocation().getY();
        int element2PosY = element2.getLocation().getY();
        if (element1PosY < element2PosY) {
            System.out.println(" Element 1 is located above Element 2 on the page");
        } else if (element1PosY > element2PosY) {
            System.out.println(" Element 1 is located above Element 2 on the page");
        } else {
            System.out.println(" The elements Y position is the same");
        }
    }
    public static void compareElementsX ( WebElement element1, WebElement element2){
        int element1PosX = element1.getLocation().getX();
        int element2PosX = element2.getLocation().getX();
        if (element1PosX < element2PosX) {
            System.out.println(" Element 1 is located to the left of Element 2 on the page");
        } else if (element1PosX > element2PosX) {
            System.out.println(" Element 1 is located to the right of Element 2 on the page");
        } else {
            System.out.println(" The elements X position is the same");
        }
    }
    public static void comapreElementsSize(WebElement element1 , WebElement element2){
       Dimension element1Size = element1.getSize();
        Dimension element2Size = element2.getSize();
       if ( element1Size.getHeight() > element2Size.getHeight()|| element1Size.getWidth()> element2Size.getWidth()){
           System.out.println(" Element 1 is bigger than Element 2 ");
        }else if  (element1Size.getHeight() <element2Size.getHeight() || element1Size.getWidth()< element2Size.getHeight() ){
            System.out.println("Element  is smaller than Element 2");
        }else {
            System.out.println(" Elements have the same size");
        }
    }
}
