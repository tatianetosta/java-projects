
/**
 * This class describes a course.
 * 
 * @author Tatiane Ferreira
 * @version 1.0
 */
public class Course
{
    private String department;
    private int number;
    private String name;
    private double price;
    private boolean isFull;
    private static final String SCHOOL_NAME = "BCIT";
    private static String motto = "To Each Their Highest Attainment";
    
    private final int MIN_NUMBER_ALLOWED = 1000;
    private final int MAX_NUMBER_ALLOWED = 6000;
    private final double MIN_PRICE_ALLOWED  = 400.0;
    private final double MAX_PRICE_ALLOWED  = 500.0;
    
    /**
     * Default constructor
     */
    public Course()
    {
    }
    
    /**
     * Another constructor
     * 
     * @param    newDepartment    - new course's department
     * @param    newNumber        - new course's number
     * @param    newName          - new course's name
     * @param    newPrice         - new course's price
     * @param    newIsFull        - new course's status (full or not)
     */
    public Course(String newDepartment, int newNumber, String newName,
                double newPrice, boolean newIsFull)
    {
        setDepartment(newDepartment);
        setNumber(newNumber);
        setName(newName);
        setPrice(newPrice);
        setIsFull(newIsFull);
    }

    /**
     * @return     the course's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return     the course's department
     */
    public String getDepartment()
    {
        return department;
    }
    
    /**
     * @return     the course's number
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * @return     the course's price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * @return     true if the course is full; false otherwise
     */
    public boolean isFull()
    {
        return isFull;
    }
    
    /**
     * @return     the course's motto
     */
    public static String getMotto()
    {
        return motto;
    }
    
    /**
     * @return     the course's school's name
     */
    public static String getSCHOOL()
    {
        return SCHOOL_NAME;
    }
    
    /**
     * @param    newDepartment    - new course's department
     */
    public void setDepartment(String newDepartment)
    {
        if((newDepartment == null) || 
           !((newDepartment.equals("COMP")) || 
             (newDepartment.equals("COMM")) || 
             (newDepartment.equals("MATH"))))
        {
            System.out.println(newDepartment + " is not a valid department.");
        }
        else
        {
            department = newDepartment;
        }
    }
    
    /**
     * @param    newNumber    - new course's number
     */
    public void setNumber(int newNumber)
    {
        if(newNumber < MIN_NUMBER_ALLOWED)
        {
            System.out.println(newNumber + " as a course number is too low.");
        }
        else if(newNumber > MAX_NUMBER_ALLOWED)
        {
            System.out.println(newNumber + " as a course number is too high.");
        }
        else
        {
            number = newNumber;
        }
    }
    
    /**
     * @param    newName    - new course's name
     */
    public void setName(String newName)
    {
        if((newName == null) || (newName.length() == 0))
        {
            System.out.println("A course name was not provided.");
        }
        else
        {
            name = newName;
        }
    }
    
    /**
     * @param    newPrice    - new course's price
     */
    public void setPrice(double newPrice)
    {
        if(newPrice < MIN_PRICE_ALLOWED)
        {
            System.out.println(newPrice + " for the price is too low.");
        }
        else if(newPrice > MAX_PRICE_ALLOWED)
        {
            System.out.println(newPrice + " for the price is too high.");
        }
        else
        {
            price = newPrice;
        }
    }
    
    /**
     * @param    newIsFull    - true if the course is full; false otherwise
     */
    public void setIsFull(boolean newIsFull)
    {
        isFull = newIsFull;
    }
    
    /**
     * @param    newMotto    - new course's motto
     */
    public void setMotto(String newMotto)
    {
        motto = newMotto;
    }
    
    /**
     * Print the course details
     */
    public void getCourseDetails() 
    {
        if(number == 0)
        {
            System.out.println("Sorry, there is an error with the course number.");
        }
        else if(name == null)
        {
            System.out.println("Sorry, there is an error with the course name.");
        }
        else if(department == null)
        {
            System.out.println("Sorry, there is an error with the course department.");
        }
        else if(price == 0)
        {
            System.out.println("Sorry, there is an error with the course price.");
        }
        else
        {
            System.out.println(department + " " + number + " is " + name + ".");
        
            if(isFull)
            {
                System.out.println("The course currently is full.");
            }
            else
            {
                System.out.println("The course currently is not full.");
            }
        
            System.out.print("The course is being run at " + SCHOOL_NAME + ", ");
            System.out.println("where their motto is \"" + motto + "\"");
        }
    }
    
}
