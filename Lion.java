import com.dennis.zoo.Animal;

public class Lion extends Animal {
    public static int numOfLion = 0;

    public Lion(String sex, int age, int weight, String animalName,
                String animalID, String animalBirthDate, String animalColor,
                String animalOrigin, String animalArrivalDate) {
        super(sex, age, weight, animalName, animalID, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
        numOfLion++;
    }
}