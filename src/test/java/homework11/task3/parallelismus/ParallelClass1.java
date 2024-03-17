package homework11.task3.parallelismus;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class ParallelClass1 {
    @Test (priority = 1)
    public void parralel1() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test(priority = 2)
    public void parralel2() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test(priority = 3)
    public void parralel3() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test(priority = 4)
    public void parralel4() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test(priority = 5)
    public void parralel5() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
}
