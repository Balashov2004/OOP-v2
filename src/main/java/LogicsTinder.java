import java.util.HashMap;


public class LogicsTinder {
    private Form formObject = new Form();
    private RegistrationUsers registrationUsers = new RegistrationUsers();
    private UserExistsChecker userExistsChecker = new UserExistsChecker();
    private Tinder tinder = new Tinder();
    private HashMap<String, HashMap<String, String>> profiles = new HashMap<>();

    private Bot bot;

    public LogicsTinder(Bot bot) {
        this.bot = bot;
    }

    private DictionaryValue dictionaryValue = new DictionaryValue();
    public void logics(String chatID, String text){

        if (!dictionaryValue.checker(chatID)) {
            dictionaryValue.create(chatID);
        }

        int stage = dictionaryValue.getStage(chatID);

        if(!profiles.containsKey(String.valueOf(chatID))){
            HashMap<String, String> profile = new HashMap<>();
            profiles.put(String.valueOf(chatID), profile);
        }

        HashMap<String, String> profil = profiles.get(String.valueOf(chatID));
        if (userExistsChecker.checkUserExistence(String.valueOf(chatID))) {
            bot.ptintToTg(Long.valueOf(chatID), tinder.tinder(chatID));
        }

        else {
            if (stage < 9) {
                stage += 1;
                dictionaryValue.save(chatID, stage, 1);
                profil = formObject.form(String.valueOf(chatID), profil, stage, text);
                profiles.put(String.valueOf(chatID), profil);
            }
            else {
                stage = -2;
                dictionaryValue.save(chatID, stage, 0);;
                registrationUsers.signUpUser(profil);
                profil.clear();
                bot.ptintToTg(Long.valueOf(chatID), "Добавлен новый пользователь");
            }
        }
    }
}
