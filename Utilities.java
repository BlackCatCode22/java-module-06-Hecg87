import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {

    public static String genBirthDay(int age, String theSeason) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int currentYear = Integer.parseInt(yearFormat.format(new Date()));

        int birthYear = currentYear - age;
        String season = (theSeason == null) ? "" : theSeason.toLowerCase();
        String birthDate;

        switch (season) {
            case "spring":
                birthDate = birthYear + "-03-21";
                break;
            case "summer":
                birthDate = birthYear + "-06-21";
                break;
            case "fall":
                birthDate = birthYear + "-09-21";
                break;
            case "winter":
                birthDate = birthYear + "-12-21";
                break;
            default:
                birthDate = birthYear + "-01-01";
                break;
        }

        return birthDate;
    }

    public static String arrivalDate() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(today);
    }

    public static String calcAnimalID(String animalSpecies) {
        String myID = "";
        if (animalSpecies.contains("hy")) {
            int myNumOfHyenas = Hyena.numOfHyena + 1;
            myID = "Hy" + String.format("%02d", myNumOfHyenas);
        }
        // You can add lion/tiger/bear ID logic here too
        return myID;
    }

    public static AnimalNameListsWrapper createAnimalNameLists(String filePath) {
        ArrayList<String> hyenaNameList = new ArrayList<>();
        ArrayList<String> lionNameList = new ArrayList<>();
        ArrayList<String> tigerNameList = new ArrayList<>();
        ArrayList<String> bearNameList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> currentList = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("Hyena Names:")) {
                    currentList = hyenaNameList;
                } else if (line.equals("Lion Names:")) {
                    currentList = lionNameList;
                } else if (line.equals("Tiger Names:")) {
                    currentList = tigerNameList;
                } else if (line.equals("Bear Names:")) {
                    currentList = bearNameList;
                } else if (!line.isEmpty()) {
                    String[] names = line.split(",\\s*");
                    for (String name : names) {
                        if (currentList != null) {
                            currentList.add(name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return new AnimalNameListsWrapper(hyenaNameList, lionNameList, tigerNameList, bearNameList);
    }
}