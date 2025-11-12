import com.google.gson.JsonObject;

public class SingleStarPojo {

    private String starId;
    private String starName;
    private String starBirthYear;
    private String movieId;
    private String movieTitle;
    private String movieYear;
    private String movieDirector;

    private SingleStarPojo() {}

    static SingleStarPojo builder() {
        return new SingleStarPojo();
    }

    public SingleStarPojo setStarId(String starId) {
        this.starId = starId;
        return this;
    }

    public SingleStarPojo setStarName(String starName) {
        this.starName = starName;
        return this;
    }

    public SingleStarPojo setStarBirthYear(String starBirthYear) {
        this.starBirthYear = starBirthYear;
        return this;
    }

    public SingleStarPojo setMovieId(String movieId) {
        this.movieId = movieId;
        return this;
    }

    public SingleStarPojo setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
        return this;
    }

    public SingleStarPojo setMovieYear(String movieYear) {
        this.movieYear = movieYear;
        return this;
    }

    public SingleStarPojo setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
        return this;
    }

    JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("star_id", this.starId);
        jsonObject.addProperty("star_name", this.starName);
        jsonObject.addProperty("star_dob", this.starBirthYear);
        jsonObject.addProperty("movie_id", this.movieId);
        jsonObject.addProperty("movie_title", this.movieTitle);
        jsonObject.addProperty("movie_year", this.movieYear);
        jsonObject.addProperty("movie_director", this.movieDirector);
        return jsonObject;
    }
}
