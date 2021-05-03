import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

public class anime {
    private String name;
    private String score;
    private String genra;
    private String ename;
    private String type;
    private String epis;
    private String aired;
    private String stud;
    private String source;
    private String duration;
    private String rating;
   public anime(){
        this.name = "";
        this.genra = "";
        this.score = "";
        this.ename = "";
        this.type = "";
        this.epis = "";
        this.aired = "";
        this.stud = "";
        this.source = "";
        this.duration = "";
        this.rating   = "";

    }
   public anime(String name, String score, String genra, String ename, String type, String epis, String aired, String stud, String source, String duration, String rating){
       this.name = name;
       this.genra = genra;
       this.score = score;
       this.ename =ename;
       this.type = type;
       this.epis = epis;
       this.aired = aired;
       this.stud = stud;
       this.source = source;
       this.duration = duration;
       this.rating   = rating;
   }

   public String getName(){
       return name;
   }
   public String getScore(){
       return score;
   }
   public String getGenra(){
       return genra;
   }
   public  String getEname(){
       return ename;
   }
    public  String getEpis(){
        return epis;
    }
    public  String getType(){
        return type;
    }
    public  String getStud(){
        return stud;
    }
    public  String getSource(){
        return source;
    }
    public  String getDuration(){
        return duration;
    }
    public  String getRating(){
        return rating;
    }
    public  String getAired(){
        return aired;
    }
    public void setName(String name){
       this.name = name;
   }
    public void setGenra(String genra){
        this.genra = genra;
    }
    public void setScore(String score){
        this.name = score;
    }
    public void setEname( String ename){
        this.ename = ename;
    }
    public  void setEpis( String epis){
        this.epis = epis;
    }
    public  void setType( String type){
        this.type = type;
    }
    public  void setStud( String stud){
        this.stud = stud;
    }
    public void setSource( String source){
        this.source =source;
    }
    public  void setDuration( String duration){
        this.duration =duration;
    }
    public void setRating( String rating){
        this.rating =rating ;
    }
    public void setAired( String aired){
        this.aired = aired;
    }
}
