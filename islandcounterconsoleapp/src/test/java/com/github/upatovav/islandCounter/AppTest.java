package com.github.upatovav.islandCounter;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException
    {
        compareFileResults("src/test/resources/clockviseSpiralTest.txt");
        compareFileResults("src/test/resources/clockviseSpiralTest2.txt");
        compareFileResults("src/test/resources/counterClockviseSpiralTest.txt");
        compareFileResults("src/test/resources/counterClockviseSpiralTest2.txt");
        compareFileResults("src/test/resources/testcase1.txt");
        compareFileResults("src/test/resources/testcase3.txt");
        compareFileResults("src/test/resources/testcase4.txt");
        compareFileResults("src/test/resources/testcase5.txt");
        compareFileResults("src/test/resources/testcase6.txt");
        compareFileResults("src/test/resources/testcase8.txt");
    }

    private void compareFileResults(final String filePath) throws IOException{
        File file = new File(filePath);
        Assert.assertEquals(
            InputStreamParser.parseInputStream(new FileReader(file),
                    (rowCount, columnCount) -> new RowReadingIslandCounter(rowCount)),
            InputStreamParser.parseInputStream(new FileReader(file),
                    (rowCount, columnCount) -> new CyclePathFindingIslandCounter(rowCount, columnCount))
        );
    }

    public void testStackDidntOverflow(){
        boolean[][] field = new boolean[5000][5000];
        for (boolean[] subarray : field) {
            Arrays.fill(subarray, true);
        }
        AbstractPathFindingIslandCounter counter
                = new CyclePathFindingIslandCounter(5000, 5000, field);
        try {
            Assert.assertEquals(counter.getIslandCount(),1);
        } catch (Exception e) {
            Assert.fail();
        }
    }
 //Commented out - execution took too long
/*
    public void testFileLoading(){
        File file = new File(
                "src/test/resources/bigFilledFile.txt");
        final ConsoleApp app =new ConsoleApp();
        final String[] args = {file.getAbsolutePath()} ;
        app.main(args);
    }
*/
}
