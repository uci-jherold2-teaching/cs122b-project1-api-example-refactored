import com.google.gson.JsonObject;

public class StarPojo {
    private String  starId;
    private String starName;
    private String starBirthYear;

    private StarPojo() {}

    public static StarPojo builder() {
        return new StarPojo();
    }

    public StarPojo setStarId(String starId) {
        this.starId = starId;
        return this;
    }

    public StarPojo setStarName(String starName) {
        this.starName = starName;
        return this;
    }

    public StarPojo setStarBirthYear(String starBirthYear) {
        this.starBirthYear = starBirthYear;
        return this;
    }

    JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("star_id", starId);
        jsonObject.addProperty("star_name", starName);
        jsonObject.addProperty("star_dob", starBirthYear);
        return jsonObject;
    }
}
