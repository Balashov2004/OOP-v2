import java.util.HashMap;
import java.util.Map;

public class DictionaryValue {
    private Map<String, Integer[]> dictionary = new HashMap<>();


    public void create(String chatID){
        Integer[] values = {-2, 0};
        dictionary.put(chatID, values);
    }

    public Boolean checker(String chatID){
        if (dictionary.containsKey(String.valueOf(chatID)))
            return true;
        else
            return false;
    }

    public void save(String chatID, Integer stage, Integer flag){
        Integer[] value = {stage, flag};
        dictionary.put(chatID, value);
    }

    public int getStage(String chatID){
        return dictionary.get(chatID)[0];
    }

    public int getFlag(String chatID){
//        System.out.println(dictionary);
        return dictionary.get(chatID)[1];
    }


}