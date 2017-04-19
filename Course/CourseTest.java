import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.ComparisonFailure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Properties;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CourseTest
{
    private final static String NEWLINE_CHAR = System.getProperties().getProperty("line.separator"); // Get the println separator
    private final static String EXPECTED_GET_BAD_NUMBER_DETAILS =  "Sorry, there is an error with the course number." + NEWLINE_CHAR;
    private final static String EXPECTED_GET_BAD_NAME_DETAILS = "Sorry, there is an error with the course name." + NEWLINE_CHAR;
    private final static String EXPECTED_GET_BAD_DEPARTMENT_DETAILS = "Sorry, there is an error with the course department." + NEWLINE_CHAR;
    private final static String EXPECTED_GET_BAD_PRICE_DETAILS = "Sorry, there is an error with the course price." + NEWLINE_CHAR;
    
    private final static String DEFAULT_NAME = null;
    private final static String DEFAULT_DEPARTMENT = null;
    private final static int DEFAULT_NUMBER = 0;
    private final static double DEFAULT_PRICE = 0.0;
    private final static boolean DEFAULT_IS_FULL = false;
    
    private final static String GOOD_NAME = "Intro to Java";
    private final static String GOOD_DEPARTMENT = "COMP";
    private final static String GOOD_DEPARTMENT_TWO = "COMM";
    private final static String GOOD_DEPARTMENT_THREE = "MATH";
    private final static int GOOD_NUMBER = 1409;
    private final static double GOOD_PRICE = 418.0;
    private final static boolean GOOD_IS_FULL = true;
    
    public CourseTest()
    {}
    
    @BeforeClass
    public static void testSetup() 
    {}
    
    @AfterClass
    public static void testCleanup()
    {
        // Teardown for data used by the unit tests
    }
    
    @Test
    public void TestFieldsHaveCorrectModifiers() {      
        
        testFieldIsPrivate("name");
        testFieldIsPrivate("department");
        testFieldIsPrivate("number");
        testFieldIsPrivate("price");
        testFieldIsPrivate("isFull");
        testFieldIsPrivate("SCHOOL_NAME");
        testFieldIsPrivate("motto");

        testFieldIsStatic("SCHOOL_NAME");
        testFieldIsStatic("motto");

        testFieldIsConstant("SCHOOL_NAME");
    }
    
    private void testFieldIsPrivate(String fieldName)
    {
        if(fieldName == null)
        {
            return;
        }
        try{
            //load the Lamborghini at runtime
            Class cls = Class.forName("Course");
            Object obj = cls.newInstance();

            //call the printItString method, pass a String param 
            Field method = cls.getDeclaredField(fieldName);
            int modifiers = method.getModifiers();
            assertEquals("Field " + fieldName + " is not private -1 mark. \n", true, Modifier.isPrivate(modifiers));
        } 
        catch (NoSuchFieldException e) {
            throw new AssertionError("Field must be named exactly \"" + fieldName + "\" -1 mark. \n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void testFieldIsConstant(String fieldName)
    {
        if(fieldName == null)
        {
            return;
        }
        try{
            //load the Lamborghini at runtime
            Class cls = Class.forName("Course");
            Object obj = cls.newInstance();

            //call the printItString method, pass a String param 
            Field method = cls.getDeclaredField(fieldName);
            int modifiers = method.getModifiers();
            assertEquals("Field " + fieldName + " is not marked constant -1 mark. \n", true, Modifier.isFinal(modifiers));
        } 
        catch (NoSuchFieldException e) { // Field does not exist
            throw new AssertionError("Field must be named exactly \"" + fieldName + "\" -1 mark. \n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void testFieldIsStatic(String fieldName)
    {
        if(fieldName == null)
        {
            return;
        }
        try{
            //load the Lamborghini at runtime
            Class cls = Class.forName("Course");
            Object obj = cls.newInstance();

            //call the printItString method, pass a String param 
            Field method = cls.getDeclaredField(fieldName);
            int modifiers = method.getModifiers();
            assertEquals("Field " + fieldName + " is not static -1 mark. \n", true, Modifier.isStatic(modifiers));
        } 
        catch (NoSuchFieldException e) {
            throw new AssertionError("Field must be named exactly \"" + fieldName + "\" -1 mark. \n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void TestStaticAccessorMethods()
    {
        Class noparams[] = {};

        try{
            Class cls = Class.forName("Course");
            Object obj = cls.newInstance();

            //call the printItString method, pass a String param 
            Method getManufacturer = cls.getDeclaredMethod("getSCHOOL", noparams);
            Method getWebpage = cls.getDeclaredMethod("getMotto", noparams);
            int manufactuerModifiers = getManufacturer.getModifiers();
            int webpageModifiers = getWebpage.getModifiers();

            assertEquals("Method \"getSCHOOL()\" is not marked static. -1 mark. \n", true, Modifier.isStatic(manufactuerModifiers));
            
            assertEquals("Method \"getMotto()\" is not marked static. -1 mark. \n", true, Modifier.isStatic(webpageModifiers));
        } 
        catch (NoSuchMethodException e) { // Field does not exist
            throw new AssertionError("Method \"" + e.getLocalizedMessage() + "\" does not exist, or is spelled wrong. -1 mark. \n");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void TestConstructorInvalidDepartmentPrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedInvalidDepartmentOutput =    "ECOM is not a valid department." + NEWLINE_CHAR;

        Course course1 = new Course("ECOM", GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Create an object with JUST a bad department

        assertEquals("Constructor does not output the correct error string when ECOM is given as the department. -2 marks. \n",
                    expectedInvalidDepartmentOutput, outContent.toString());    
        //Flush the collected content
        outContent.reset();
        ps.flush();
    }

    @Test
    public void TestConstructorNumberLowValuePrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedNumberLowValueOutput =    "100 as a course number is too low." + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, 100, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Create an object with JUST a low course number
        assertEquals("Constructor does not output the correct error string when 100 is given as the course number. -2 marks. \n",
                    expectedNumberLowValueOutput, outContent.toString());

        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    @Test
    public void TestConstructorNumberHighValuePrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedNumberLHighValueOutput =    "9000 as a course number is too high." + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, 9000, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Create an object with JUST a low course number
        assertEquals("Constructor does not output the correct error string when 9000 is given as the course number. -2 marks. \n",
                    expectedNumberLHighValueOutput, outContent.toString());

        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    @Test
    public void TestConstructorNameEmptyPrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedNameEmptyValueOutput =    "A course name was not provided." + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, "", GOOD_PRICE, true);
        assertEquals("Constructor does not output the correct error string when an empty string is given as the model name. -3 marks. \n",
                    expectedNameEmptyValueOutput, outContent.toString());

        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    @Test
    public void TestConstructorPriceLowValuePrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedPriceLowValueOutput =    "100.0 for the price is too low." + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 100, GOOD_IS_FULL); // Create an object with JUST a low course number
        assertEquals("Constructor does not output the correct error string when 100 is given as the course number. -2 marks. \n",
                    expectedPriceLowValueOutput, outContent.toString());

        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    @Test
    public void TestConstructorPriceHighValuePrintln()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedPriceHighValueOutput =    "9000.18 for the price is too high." + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 9000.18, GOOD_IS_FULL); // Create an object with JUST a low course number
        assertEquals("Constructor does not output the correct error string when 9000 is given as the course number. -2 marks. \n",
                    expectedPriceHighValueOutput, outContent.toString());

        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    @Test
    public void TestConstructorDepartmentParameter() 
    {
        try {
            Course course1 = new Course(null, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Validation for department does not accept a null correctly. -2 marks.\n", null, course1.getDepartment());
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Constructor crashes when department is null. -2 marks. \n");
        }
        
        try {
            Course course2 = new Course("", GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Validation for department does not accept an empty string (i.e. \"\") correctly. -2 marks.\n", null, course2.getDepartment());
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Constructor crashes when you pass in an empty string (\"\"). -2 marks. \n");
        }
        
        try {
            Course course3 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Department is not set correctly in the Constructor. -2 marks.\n", GOOD_DEPARTMENT, course3.getDepartment());
        }
        catch (java.lang.NullPointerException e)
        {
            throw new AssertionError("Unidentified crash prevents department from being assigned. -2 marks. \n");
        }
        try {
            Course course4 = new Course(GOOD_DEPARTMENT_TWO, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Department is not set correctly in the Constructor. -2 marks.\n", GOOD_DEPARTMENT_TWO, course4.getDepartment());
        }
        catch (java.lang.NullPointerException e)
        {
            throw new AssertionError("Unidentified crash prevents department from being assigned. -2 marks. \n");
        }
        try {
            Course course5 = new Course(GOOD_DEPARTMENT_THREE, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Department is not set correctly in the Constructor. -2 marks.\n", GOOD_DEPARTMENT_THREE, course5.getDepartment());
        }
        catch (java.lang.NullPointerException e)
        {
            throw new AssertionError("Unidentified crash prevents department from being assigned. -2 marks. \n");
        }
    }
    
    @Test
    public void TestConstructorNumberParameter() {
        try 
        {
            Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            Course course2 = new Course(GOOD_DEPARTMENT, 1000, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Low end(inclusive) of the ranges
            Course course3 = new Course(GOOD_DEPARTMENT, 6000, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // High end(inclusive) of the ranges
            Course course4 = new Course(GOOD_DEPARTMENT, 999, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Below low extreme
            Course course5 = new Course(GOOD_DEPARTMENT, 6001, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); // Below high extreme
            
            assertEquals("The course number is not set correctly in the Constructor. -2 marks.\n", GOOD_NUMBER, course1.getNumber());
            assertEquals("number does not accept a valid input value of 1000. -2 marks.\n", 1000, course2.getNumber());
            assertEquals("number does not accept a valid input value of 6000. -2 marks.\n", 6000, course3.getNumber());
            assertEquals("number does not reject an invalid input value of 999. -2 marks.\n", 0, course4.getNumber());
            assertEquals("number Year does not reject an invalid input value of 6001. -2 marks.\n", 0, course5.getNumber());
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Unidentified crash prevents number from being assigned. -2 marks. \n");
        }
    }
    
    @Test
    public void TestConstructorNameParameter() 
    {
        try {
            Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, null, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Validation for name does not accept a null correctly. -2 marks.\n", null, course1.getName());
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Constructor crashes when name is null. -2 marks. \n");
        }
        
        try {
            Course course2 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, "", GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Validation for name does not accept an empty string (i.e. \"\") correctly. -2 marks.\n", null, course2.getName());
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Constructor crashes when you pass in an empty string (\"\"). -2 marks. \n");
        }
        
        try {
            Course course3 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("name is not set correctly in the Constructor. -2 marks.\n", GOOD_NAME, course3.getName());
        }
        catch (java.lang.NullPointerException e)
        {
            throw new AssertionError("Unidentified crash prevents name from being assigned. -2 marks. \n");
        }
    }
    
    @Test
    public void TestConstructorPriceParameter() 
    {
        try 
        {
            Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            Course course2 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 400.0, GOOD_IS_FULL); // Low end(inclusive) of the ranges
            Course course3 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 500.0, GOOD_IS_FULL); // High end(inclusive) of the ranges
            Course course4 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 399.0, GOOD_IS_FULL); // Below low extreme
            Course course5 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, 501.0, GOOD_IS_FULL); // Below high extreme
            
            assertEquals("The price is not set correctly in the Constructor. -2 marks.\n", GOOD_PRICE, course1.getPrice(), 0.1);
            assertEquals("price does not accept a valid input value of 400. -2 marks.\n", 400.0, course2.getPrice(), 0.1);
            assertEquals("price does not accept a valid input value of 500. -2 marks.\n", 500.0, course3.getPrice(), 0.1);
            assertEquals("price does not reject an invalid input value of 399. -2 marks.\n", 0.0, course4.getPrice(), 0.1);
            assertEquals("price Year does not reject an invalid input value of 501. -2 marks.\n", 0.0, course5.getPrice(), 0.1);
        }
        catch (java.lang.Exception e)
        {
            throw new AssertionError("Unidentified crash prevents price from being assigned. -2 marks. \n");
        }
    }
    
    @Test
    public void TestConstructorIsFullParameter() {
        try {
            Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL);
            assertEquals("Is Full does not accept a valid input value of true. -1 mark.\n", true, course1.isFull());
        }
        catch (java.lang.Exception e)
        {
             throw new AssertionError("Unidentified crash prevents isFull from being assigned. -1 mark. \n");
        }
    }
    
    @Test
    public void TestGetDetailsStringWithGoodValues()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        String expectedOutput =     "COMP 1409 is Intro to Java." + NEWLINE_CHAR + 
                                    "The course currently is full." + NEWLINE_CHAR +
                                    "The course is being run at BCIT, where their motto is \"To Each Their Highest Attainment.\"" + NEWLINE_CHAR;

        Course course1 = new Course(GOOD_DEPARTMENT, GOOD_NUMBER, GOOD_NAME, GOOD_PRICE, GOOD_IS_FULL); //Object with valid everything
        course1.setMotto("To Each Their Highest Attainment.");
        course1.getCourseDetails();
        assertEquals("Constructor does not output the details string correctly. -4 marks. \n",
                    expectedOutput, outContent.toString());    
        //Flush the collected content
        outContent.reset();
        ps.flush();
    }
    
    private String obtainGetDetailsOutput(Course testSubject)
    {
        String theOutput;
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);

        //Flush the collected content
        outContent.reset();
        ps.flush();
        
        try
        {
            testSubject.getCourseDetails();
            theOutput = outContent.toString();
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            //Flush the collected content
            outContent.reset();
            ps.flush();
        }
        
        return theOutput;
    } // end method

    @Test
    public void TestGetCourseDetailsStringWithBadNumber()
    {
        Course course1;
        try {
             course1 = new Course(); // Create an object with JUST a bad number
             course1.setName(GOOD_NAME);
             course1.setDepartment(GOOD_DEPARTMENT);
             course1.setPrice(GOOD_PRICE);
        }
        catch (Exception e)
        {
            throw new AssertionError("A crash during Course object construction prevents this test. -2 marks. \n");
        }
        
        try {
            assertEquals("Print details does not output the correct error string when 0 is given as the number. -2 marks. \n",
                    EXPECTED_GET_BAD_NUMBER_DETAILS, obtainGetDetailsOutput(course1));
        }
        catch (Exception e)
        {
            throw new AssertionError("Print Details crashes when the number is 0. -2 marks. \n");
        }
    }
    
    @Test
    public void TestGetDetailsStringWithBadName()
    {
        Course course1;
        try {
             course1 = new Course(); // Create an object with JUST a bad name
             course1.setNumber(GOOD_NUMBER);
             course1.setDepartment(GOOD_DEPARTMENT);
             course1.setPrice(GOOD_PRICE);
        }
        catch (Exception e)
        {
            throw new AssertionError("A crash during Course object construction prevents this test. -2 marks. \n");
        }
        
        try {
            assertEquals("Print Details does not output the correct error string when the name is null. -2 marks. \n",
                    EXPECTED_GET_BAD_NAME_DETAILS, obtainGetDetailsOutput(course1));
        }
        catch (Exception e)
        {
            throw new AssertionError("Print Details crashes when the name is null. -2 marks. \n");
        }
    }
    
    @Test
    public void TestGetDetailsStringWithBadDepartment()
    {
        Course course1;
        try {
             course1 = new Course(); // Create an object with JUST a bad department
             course1.setNumber(GOOD_NUMBER);
             course1.setName(GOOD_NAME);
             course1.setPrice(GOOD_PRICE);
        }
        catch (Exception e)
        {
            throw new AssertionError("A crash during Course object construction prevents this test. -2 marks. \n");
        }
        
        try {
            assertEquals("Print Details does not output the correct error string when the name is null. -2 marks. \n",
                    EXPECTED_GET_BAD_DEPARTMENT_DETAILS, obtainGetDetailsOutput(course1));
        }
        catch (Exception e)
        {
            throw new AssertionError("Print Details crashes when the department is null. -2 marks. \n");
        }
    }
    
    @Test
    public void TestGetCourseDetailsStringWithBadPrice()
    {
        Course course1;
        try {
             course1 = new Course(); // Create an object with JUST a bad number
             course1.setName(GOOD_NAME);
             course1.setDepartment(GOOD_DEPARTMENT);
             course1.setNumber(GOOD_NUMBER);
        }
        catch (Exception e)
        {
            throw new AssertionError("A crash during Course object construction prevents this test. -2 marks. \n");
        }
        
        try {
            assertEquals("Print details does not output the correct error string when 0.0 is given as the number. -2 marks. \n",
                    EXPECTED_GET_BAD_PRICE_DETAILS, obtainGetDetailsOutput(course1));
        }
        catch (Exception e)
        {
            throw new AssertionError("Print Details crashes when the price is 0.0. -2 marks. \n");
        }
    }
    
        @Test
    public void TestGetDetailsStringWithBadEverything()
    {
        Course course1;
        try {
             course1 = new Course(DEFAULT_DEPARTMENT, DEFAULT_NUMBER, DEFAULT_NAME, DEFAULT_PRICE, DEFAULT_IS_FULL); // as you can see from the constant names, none of these are "good"
        }
        catch (Exception e)
        {
            return;
        }
        
        try {
            String output = obtainGetDetailsOutput(course1);
            boolean hasOneErrorButNothingElse = EXPECTED_GET_BAD_NUMBER_DETAILS.equals(output)
                || EXPECTED_GET_BAD_NAME_DETAILS.equals(output)
                || EXPECTED_GET_BAD_PRICE_DETAILS.equals(output)
                || EXPECTED_GET_BAD_DEPARTMENT_DETAILS.equals(output);
            
            assertEquals("Print Details does not output the correct error string when multiple bad fields are encountered. -5 marks. \n", true, hasOneErrorButNothingElse);
        }
        catch (Exception e)
        {
            return;
        }
    }
    
    

}