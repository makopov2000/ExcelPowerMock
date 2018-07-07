package com.powermock.test4;


import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class) 
@PrepareForTest(HelloStatic.class)
public class GreetingInstanceTest {
     
    @Test
    public void echo() 
    {
        String shrubbery = "Shrubbery";
        String expected = "Shrubbery, Rita Red";
        String actual = null;
         
        PowerMock.mockStatic(HelloStatic.class);
        EasyMock.expect(HelloStatic.getHello()).andReturn(shrubbery);
        PowerMock.replay(HelloStatic.class);
        {
            actual = new GreetingInstance().getGreeting("Rita Red");
        }       
        PowerMock.verify(HelloStatic.class);
        Assert.assertEquals(expected, actual);
    }    
}
