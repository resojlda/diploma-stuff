package charts;

import com.googlecode.vkapi.convert.JsonConverter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class PieChartData {
    private ArrayList<Data> array;

    public PieChartData() {
        this.array = new ArrayList<>();
    }

    public ArrayList<Data> getArray() {
        return array;
    }

    public void setArray(ArrayList<Data> array) {
        this.array = array;
    }
    
    public void addData(Data data){
        array.add(data);
    }
    
    public JsonNode toJson() throws IOException{
        JsonConverter converter = new JsonConverter();
        
        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, array);
        JsonNode json = converter.toJson(sw.toString());
        sw.close();
        
        return json;
    }
    
    public static void main(String[] args) throws IOException {
        PieChartData pcd = new PieChartData();
        pcd.addData(new Data("f", 1));
        pcd.addData(new Data("c", 2));
        pcd.addData(new Data("a", 3));
        
        System.out.println(pcd.toJson().toString());
    }
}
