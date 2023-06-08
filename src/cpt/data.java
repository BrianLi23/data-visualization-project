package cpt;

public class data {
    
    // Private instance variable
    private String strEntity;
    private int intYear;
    private int intNumberPub;


    /**
     * Constructor for data
     * 
     * @param strEntity      Entity of AI data
     * @param intYear        The year of data
     * @param intNumberPub   Number of publications
     * 
     */
    public data(String strEntity, int intYear, int intNumberPub){
        this.strEntity = strEntity;
        this.intYear = intYear;
        this.intNumberPub = intNumberPub;
    }

    /*
     * Get entity of data point
     * 
     * @return entity of data
     * 
     */
    public String getEntity(){
        return this.strEntity;
    }

    /*
     * Get year of data point
     * 
     * @return year
     * 
     */
    public int getYear(){
        return this.intYear;
    }

    /*
     * Get number of publications 
     * 
     * @return publications
     * 
     */
    public int getNumberPub(){
        return this.intNumberPub;
    }
}

