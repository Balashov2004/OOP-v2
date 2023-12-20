import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestTinder {

    @Test
    public void testCoincidenceOfInterests() throws IOException {
        Assert.assertEquals(50, Tinder.coincidenceOfInterests(10, 10, 10, 5, 5, 5));
        Assert.assertEquals(50, Tinder.coincidenceOfInterests(5, 5, 5, 10, 10, 10));
        Assert.assertEquals(66, Tinder.coincidenceOfInterests(3, 9, 3, 7, 5, 3));
    }

    @Test
    public void testForm() {
        String chatID = "777";
        HashMap<String, String> profile = new HashMap<>();
        int request = 0;
        String text = "test text";

        HashMap<String, String> result = Form.form(chatID, profile, request, text);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(text, result.get("login"));
    }

    @Test
    public void testGetInfoUser(){
        Map<String, String> request = GetInfoUser.getUserInfoByID(740660067);
        Map<String, String> profile = new HashMap<>();
        profile.put("login", "s_balashov_tg");
        profile.put("firstname", "Sasha");
        profile.put("lastname", "Balashov");
        profile.put("city", "Ekb");
        profile.put("age", "19");
        profile.put("gender", "men");
        profile.put("sport", "10");
        profile.put("travel", "10");
        profile.put("discos", "10");
        profile.put("aboutMe", "I play basketball");
        Assert.assertEquals(profile.get("login"), request.get("login"));
        Assert.assertEquals(profile.get("firstname"), request.get("firstname"));
        Assert.assertEquals(profile.get("lastname"), request.get("lastname"));
        Assert.assertEquals(profile.get("city"), request.get("city"));
        Assert.assertEquals(profile.get("age"), request.get("age"));
        Assert.assertEquals(profile.get("gender"), request.get("gender"));
        Assert.assertEquals(profile.get("sport"), request.get("sport"));
        Assert.assertEquals(profile.get("travel"), request.get("travel"));
        Assert.assertEquals(profile.get("discos"), request.get("discos"));
        Assert.assertEquals(profile.get("aboutMe"), request.get("aboutMe"));
    }

    @Test
    public void testUserExistsChecker(){
        Boolean request = UserExistsChecker.checkUserExistence("000000");
        Assert.assertEquals(false, request);
        request = UserExistsChecker.checkUserExistence("740660067");
        Assert.assertEquals(true, request);
    }
}
