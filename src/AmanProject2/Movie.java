package AmanProject2;

public class Movie {
    String movieName;
    String posterUrl="https://image.tmdb.org/t/p/w500/";
    String title;
    String popularity;

    public Movie(String movieName, String posterUrl, String title, String popularity) {
        this.movieName = movieName;
        this.posterUrl += posterUrl;
        this.title = title;
        this.popularity = popularity;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return
                "Movie name= '" + movieName + '\'' +
                ", PosterUrl= '" + posterUrl + '\'' +
                ", Title= '" + title + '\'' +
                ", Popularity= " + popularity;
    }
}
