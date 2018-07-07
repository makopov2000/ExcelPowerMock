package com.powermock.test2.easymock;

public class SampleService {

	  private SampleServiceHelper sampleServiceHelper = new SampleServiceHelper();
			
	  public String method1(){		
	    return "method1:" + 
	    SampleUtils.staticMethod(); 
	   //we will need to mock this static method call
	  }
		
	  public String method2(){		
	    return "method2:" + 
	    sampleServiceHelper.helperMethod(); 
	   //we will need to mock this method call on global variable that is created during class initialization
	  }
		
	  public String method3(){
	    SampleServiceHelper2 sampleServiceHelper2 = new SampleServiceHelper2();
	    return "method3:" + 
	    sampleServiceHelper2.helperMethod();
	   //we will need to mock this method call on variable that is created within this method
	  }
		
	  public String method4(){
	    return "method4:" + 
	    privateMethod("someParam");
	    //we will partially mock this private method call
	  }
		
	  public String method5(){
	    return "method5:" + 
	    SampleUtils.staticMethod() + 
	    privateMethod("someParam") +
	    sampleServiceHelper.helperMethod();
	    //we will mock all three calls in the last test
	  }
		
	  protected String privateMethod(String param){
	    return "privateMethod-" + param;
	  }
	}
