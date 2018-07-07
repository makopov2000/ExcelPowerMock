package com.powermock.test3;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PowerMockIgnore("org.apache.http.conn.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({FirstUtil.class,SecondUtil.class})
 
public class TestToMockStatic {
 String parameter = "one";
 String parameter1 = "success";
 
    @Rule
 
    @Test
        public void testProductOrderProcessing() throws IOException{
            PowerMockito.mockStatic(FirstUtil.class);//For mocking static functions
            PowerMockito.mockStatic(SecondUtil.class);
 
            Mockito.when(FirstUtil.staticFunction(parameter)).thenReturn(mockedStaticFunction(parameter1));
            Mockito.when(SecondUtil.staticFunction(parameter)).thenReturn(mockedStaticFunction(parameter1));
 
            ForTest forTest= new ForTest();
 
            assertEquals(forTest.executeFunction(),"success");
 
        }
 
        public String mockedStaticFunction(String parameter){
            return parameter;
        }
 
}
