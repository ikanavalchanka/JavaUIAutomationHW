package homework11.task3.parallelismus;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParallelClass2 {
    @Test (priority = 1)
    public void parralel6() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test (priority = 2)
    public void parralel7() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test (priority = 3)
    public void parralel8() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test(priority = 4)
    public void parralel9() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
    @Test(priority = 5)
    public void parralel10() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
}
