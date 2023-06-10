package cpt;

public class data {

    // Private instance variable
    private String strEntity;
    private int intYear;
    private String strDay;
    private int intParameters;
    private String strDomain;

    /**
     * Constructor for data
     * 
     * @param strEntity     Entity of AI data
     * @param intYear       The year of data
     * @param strDay        Day of datapoint
     * @param intParameters Number of parameteres
     * @param strDomain     Type of Domain
     * 
     */
    public data(String strEntity, int intYear, String strDay, int intParameters, String strDomain) {
        this.strEntity = strEntity;
        this.intYear = intYear;
        this.strDay = strDay;
        this.intParameters = intParameters;
        this.strDomain = strDomain;
    }

    /*
     * Get entity of data point
     * 
     * @return entity of data
     * 
     */
    public String getEntity() {
        return this.strEntity;
    }

    /*
     * Get year of data point
     * 
     * @return year
     * 
     */
    public int getYear() {
        return this.intYear;
    }

    /*
     * Get number of publications
     * 
     * @return publications
     * 
     */
    public int getNumberPub() {
        return this.intNumberPub;
    }
}
