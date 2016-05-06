package university;

import com.googlecode.vkapi.convert.JsonConverter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class CityAndUniversities {

    public static ArrayList<University> requestUniversity(long idCity) {
        JsonConverter converter = new JsonConverter();
        ArrayList<University> array = converter.getArrayUniv(getJsonUniv(idCity));
        return array;
    }

    public static URIBuilder getUriBuilderUniver(long cityId) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/database.getUniversities")
                .setParameter("country_id", "2")
                .setParameter("city_id", Long.toString(cityId))
                .setParameter("version", "5.45");

        return uriBuilder;
    }

    public static URIBuilder getUriBuilderCity() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/database.getCities")
                .setParameter("country_id", "2")
                .setParameter("count", "100")
                .setParameter("version", "5.45")
                .setParameter("lang", "ru");

        return uriBuilder;
    }

    public static String getJsonUniv(long cityId) {
        StringWriter content = new StringWriter();
        try {
            IOUtils.copy(getResponse(getUriBuilderUniver(cityId)).getEntity().getContent(), content, "UTF-8");
        } catch (IOException ex) {
        }
        return content.toString();
    }

    public static HttpResponse getResponse(URIBuilder uriBuilder) {
        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);
        request.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
        HttpResponse response = null;

        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return response;
    }

    public static JsonNode getCityJson() throws IOException {

        StringWriter content = new StringWriter();
        
        try {
            IOUtils.copy(getResponse(getUriBuilderCity()).getEntity().getContent(), content, "UTF-8");
        } catch (IOException ex) {
        }
        
        JsonConverter converter = new JsonConverter();
        ArrayList<City> array = converter.getArray(content.toString());

        for (int i = 0; i < array.size(); i++) {
            array.get(i).setArray(requestUniversity((array.get(i).getId())));
        }

        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, array);
        JsonNode json = converter.toJson(sw.toString());
        sw.close();

        return json;
    }

}
