package com.platy.todolist.entities;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EntityService {

    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static EntityService instance;


    private EntityService() {
    }


    public static EntityService getInstance() {
        if (instance == null) {
            instance = new EntityService();
        }
        return instance;
    }


    public List<Event> getEvents() {
        List<Event> eventList = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        Response response;
        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/events")
                .build();

        try {
            response = client.newCall(request).execute();
            eventList = mapper.readValue(response.body().string(), new TypeReference<List<Event>>() {
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public void putEvent(long id, Event event) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        String json = null;
        try {
            json = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json); // new
        // RequestBody body = RequestBody.create(JSON, json); // old
        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/events/" + id)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String stackTrace = e.getMessage();
                e.printStackTrace();

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                }
            }
        });
    }

    public Event getEvent(long id) {

        Event event = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        Response response;
        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/event/" + id)
                .build();

        try {
            response = client.newCall(request).execute();
            event = mapper.readValue(response.body().string(), Event.class);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }

    public List<Event> getEventsByDate(String date) {
        List<Event> eventList = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        Response response;
        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/events/" + date)
                .build();

        try {
            response = client.newCall(request).execute();
            eventList = mapper.readValue(response.body().string(), new TypeReference<List<Event>>() {
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public void addEvent(Event event) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        String json = null;
        try {
            json = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json); // new
        // RequestBody body = RequestBody.create(JSON, json); // old
        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/events")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String stackTrace = e.getMessage();
                e.printStackTrace();

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                }
            }
        });
    }

    public void deleteEvent(long id) {

        Request request = new Request.Builder()
                .url("http://192.168.43.253:8080/events/" + id)
                .delete()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String stackTrace = e.getMessage();
                e.printStackTrace();

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                }
            }
        });
    }

}

/*
class RequestHandler extends AsyncTask<Void, Integer, List<Event>> {
    @Override
    protected List<Event> doInBackground(Void... voids) {
        List<Event> eventList = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);


        try {
            URL url = new URL("http://192.168.43.253:8080/events");
            eventList = mapper.readValue(url, new TypeReference<List<Event>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        EntityService entityService = EntityService.getInstance();
        entityService.setEventList(eventList);
        return eventList;
    }
}
*/
