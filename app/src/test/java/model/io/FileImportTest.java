/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.io;

import java.util.ArrayList;
import model.map.GameMap;
import model.map.Local;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpfr8
 */
public class FileImportTest {

    public FileImportTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readLocalFile method, of class FileImport.
     */
    @Test
    public void testReadLocalFile() throws Exception {

        System.out.println("readLocalFile");
        GameMap list = FileImport.readLocalFile("cidades.txt");
        assertEquals(list.getGraph().vertexSet().size(), 11);

    }

    /**
     * Test of readRoadFile method, of class FileImport.
     */
    @Test
    public void testReadRoadFile() throws Exception {
        System.out.println("readRoadFile");
        GameMap map = FileImport.readRoadFile("roads.txt", "cidades.txt");
        System.out.println(map.getGraph());
        assertEquals(map.getGraph().vertexSet().size(), 11);
        assertEquals(map.getGraph().edgeSet().size(), 20);
    }

}
