package org.example;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;



public class GatoService {
    public static void verGatitos() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").method("GET", null).build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        json = json.substring(1,json.length()-1);

        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(json, Gatos.class);

        Image imagen = null;
        try{
            URL url = new URL(gatos.getUrl());

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            ImageIcon fondoGato = new ImageIcon(bufferedImage);

            if(fondoGato.getIconWidth() > 800 || fondoGato.getIconHeight() > 400){

                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu =  "Opciones: \n1.- Cambiar Gatitos \n2.-Favorito \n3.-Volver ";
            String[] botones = { "Ver Otra imagen", "Favoritos", "Volver"};
            String opcion = (String) JOptionPane.showInputDialog(null,menu,gatos.getId(),JOptionPane.INFORMATION_MESSAGE
                    ,fondoGato,botones,botones[0]);

            int seleccion = -1;

            for(int i = 0; i < botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }

            switch(seleccion){
                case 0:
                    verGatitos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }

        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void favoritoGato(Gatos gatos){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+gatos.getId()+"\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatos.getApikey())
                    .build();
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println(e);
        }


    }

    public static void verFavoritos(String apiKey) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        // guardamos el string con la respuesta
        String elJson = response.body().string();

        //creamos el objeto gson
        Gson gson = new Gson();

        GatosFav[] gatosArray = gson.fromJson(elJson,GatosFav[].class);

        if(gatosArray.length > 0){
            int min = 1;
            int max  = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max-min)-1)) + min;
            int indice = aleatorio-1;

            GatosFav gatofav = gatosArray[indice];


        }

    }

}
