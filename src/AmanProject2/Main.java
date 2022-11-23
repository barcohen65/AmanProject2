package AmanProject2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        //  URL url = new URL("https://api.themoviedb.org/3/movie/tt0275491?api_key=36f9f50bb6193722ba0fc46c0a6fd725");

        final String api = "https://api.themoviedb.org/3/movie/";
        final String apiKey = "36f9f50bb6193722ba0fc46c0a6fd725";

        BufferedReader reader = null;
        String line;
        ArrayList<List<String>> movieInfo = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter imdb id: ");
        String imdbId = scanner.nextLine();
        String urlUse = api + imdbId + "?api_key=" + apiKey;

        while (!imdbId.equals("0")) {
            try {
                StringBuffer responseContent = new StringBuffer();
                URL url = new URL(urlUse);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                int status = con.getResponseCode();
                if (status > 299) {
                    reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));

                } else {
                    reader = new BufferedReader(new InputStreamReader((con.getInputStream())));
                }
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                movieInfo.add(Arrays.asList(responseContent.toString().replace("{", "").replace("}", "").replace("\"", "").split("[,:]")));
                System.out.println("The title of the movie is: "+movieInfo.get(movieInfo.size()-1).get(movieInfo.get(movieInfo.size()-1).indexOf("original_title") + 1)+'\n');
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Enter imdb id: ");

            imdbId = scanner.nextLine();
            urlUse = api + imdbId + "?api_key=" + apiKey;
        }


        if (movieInfo.isEmpty()) {
            System.out.println("No movies in the list");
        } else {
            ArrayList<Movie> movies = new ArrayList<>();
            double maxPopularity = 0;
            int indexMaxPopularity = 0;

            String path = "C:" + File.separator + "temp" + File.separator + "posterAddress.txt";
            File f = new File(path);

            if(!f.createNewFile()){
                System.out.println(path+" File Not Created");
            }

            FileWriter fileWriter = new FileWriter(f,true);


            for (List<String> movieData : movieInfo) {
                Movie movie = new Movie(movieData.get(movieData.indexOf("name") + 1), movieData.get(movieData.indexOf("poster_path") + 1), movieData.get(movieData.indexOf("original_title") + 1), movieData.get(movieData.indexOf("popularity") + 1));
                movies.add(movie);
                if (Double.parseDouble(movie.popularity) > maxPopularity) {
                    maxPopularity = Double.parseDouble(movie.popularity);
                    indexMaxPopularity = movies.indexOf(movie);
                }
                try {
                   fileWriter.write(movie.movieName+" Poster url is: "+movie.posterUrl+'\n');

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            fileWriter.close();

            System.out.println("Most popular movie: "+movies.get(indexMaxPopularity));

        }

    }
}
