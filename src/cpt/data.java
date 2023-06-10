package cpt;

public class data {

    // Private instance variable
    private String strEntity;
    private int intYear;
    private String strDay;
    private int intParameter;
    private String strDomain;

    /**
     * Constructor for data
     * 
     * @param strEntity    Entity of AI data
     * @param intYear      The year of data
     * @param strDay       Day of datapoint
     * @param intParameter Number of parametere
     * @param strDomain    Type of Domain
     * 
     */
    public data(String strEntity, int intYear, String strDay, int intParameter, String strDomain) {
        this.strEntity = strEntity;
        this.intYear = intYear;
        this.strDay = strDay;
        this.intParameter = intParameter;
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
     * Get day of publications
     * 
     * @return day
     * 
     */
    public String getDay() {
        return this.strDay;
    }

    /*
     * Get parameter of datapoint
     * 
     * @return parameter
     * 
     */
    public int getParameter() {
        return this.intParameter;
    }

    /*
     * Get domain of datapoint
     * 
     * @return domain
     * 
     */
    public String getDomain() {
        return this.strDomain;
    }
}
