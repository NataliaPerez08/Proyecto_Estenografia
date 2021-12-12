package md;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.md.Converter;
import main.java.md.IOHandler;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    static Converter cv = new Converter();
    static IOHandler io = new IOHandler();
    static App app = new App();
    static String pathTestText = "resources/text/test.txt";
    static String pathTest2Text = "output/text/test2.txt";
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void firstBinaryStringTest(){
       // Converter cv = new Converter();
        String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc facilisis, nunc ac sollicitudin vulputate, eros odio luctus dui, id suscipit tellus mauris et est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec viverra dui ac mauris gravida, at tincidunt lectus efficitur. Pellentesque porta iaculis ipsum ut vestibulum. Curabitur in dui ullamcorper, tincidunt lectus hendrerit, egestas erat. Etiam ac interdum dolor. Nulla feugiat convallis turpis, et dictum ipsum vulputate vitae. Etiam sollicitudin lorem quis magna ultricies, eu bibendum diam facilisis. Morbi auctor ligula sed pulvinar pharetra. Praesent eu feugiat diam, vitae sollicitudin lorem. Ut in nunc ac nisi pharetra condimentum. Praesent euismod, augue in condimentum eleifend, eros tortor ullamcorper elit, et dictum odio sapien id lorem. In varius pellentesque magna vel condimentum. Nam consectetur, diam eget finibus imperdiet, lacus sem bibendum eros, ut accumsan magna odio nec dui. Nullam at lorem placerat, aliquet elit sed, elementum lorem. Aenean ac dui facilisis, consequat orci non, egestas mauris.";
        String tb = cv.textToBinary(message);
        String newMess = cv.binaryToText(tb);
        assertEquals(message,newMess);
    }

    @Test
    public void readTextTest(){
        String expectedText = "Este archivo se utiliza para un test. No borrar.";
        String recoverText = io.getTextFile(pathTestText);
        assertEquals(expectedText,recoverText);
    }

    @Test
    public void readWriteTextTest(){
        String expectedText = "Lorem ipsum dolor sit amet";
        io.giveTextFile(expectedText, pathTest2Text);
        String recoverText = io.getTextFile(pathTest2Text);
        assertEquals(expectedText,recoverText);
    }
}
