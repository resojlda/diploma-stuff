/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usp.university;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet("/univ")
public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            JSONObject json = getResult();
            String s = "Строка";
            request.setAttribute("json", json);
            request.setAttribute("s", s);
            //тут указываем jsp-файл, которому сервер отправляет результат запроса
            getServletContext().getRequestDispatcher("WEB-INF/views/univ.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HttpResponse getResponse(URIBuilder uriBuilder){
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
    
    public JSONArray getUniversities(long cityId) throws IOException{
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/database.getUniversities")
                .setParameter("country_id", "2")
                .setParameter("city_id", Long.toString(cityId))
                .setParameter("version", "5.45");
                //.setParameter("lang", "ru");
                        
        StringWriter content = new StringWriter();
        IOUtils.copy(getResponse(uriBuilder).getEntity().getContent(), content, "UTF-8");
        
        String result = "";
        JSONParser parser = new JSONParser();
        JSONArray postsList = new JSONArray();
        
        try {
            JSONObject jsonResp = (JSONObject) parser.parse(content.toString());
            postsList = (JSONArray) jsonResp.get("response");
            JSONObject obj = null;
            
            postsList.remove(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return postsList;
        
    }
    
    public JSONObject getResult() throws IOException, ParseException {
URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/database.getCities")
                .setParameter("country_id", "2")
                .setParameter("count", "100")
                .setParameter("version", "5.45")
                .setParameter("lang", "ru");
       
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
        } catch (IOException e) {}
        
        StringWriter content = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), content, "UTF-8");
        JSONObject result = new JSONObject();
        JSONParser parser = new JSONParser();
        try{

        JSONObject cities = (JSONObject) parser.parse(content.toString());
        JSONArray cityArray = (JSONArray) cities.get("response");
        JSONObject object = null;
        JSONArray resultArray = new JSONArray();
        
        for(int i = 0; i < cityArray.size(); i++){
            object = (JSONObject) cityArray.get(i);
            object.put("array", getUniversities((Long) object.get("cid")));
            resultArray.add(object);
        }
        result.put("response", resultArray);
             
        }
        catch(ParseException e){}
      
        return result;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
