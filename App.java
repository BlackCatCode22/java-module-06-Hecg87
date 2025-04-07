public static void main(String[] args) {
    System.out.println("Welcome to my Zoo Program!");

    String namesPath = "C://Users//Hector//Desktop//Jave//MIDTERM//src//animalNames.txt";
    String arrivalsPath = "C://Users//Hector//Desktop//Jave//MIDTERM//src//arrivingAnimals.txt";

    // Load name lists
    AnimalNameListsWrapper animalLists = Utilities.createAnimalNameLists(namesPath);
    ArrayList<String> hyenaNames = animalLists.getHyenaNameList();
    ArrayList<String> lionNames = animalLists.getLionNameList();
    ArrayList<String> tigerNames = animalLists.getTigerNameList();
    ArrayList<String> bearNames = animalLists.getBearNameList();

    // Create animal lists
    ArrayList<Hyena> hyenaList = new ArrayList<>();
    ArrayList<Lion> lionList = new ArrayList<>();
    ArrayList<Tiger> tigerList = new ArrayList<>();
    ArrayList<Bear> bearList = new ArrayList<>();

    // Read and process arriving animals
    try (BufferedReader reader = new BufferedReader(new FileReader(arrivalsPath))) {
        String line;
        int lineCount = 1;

        while ((line = reader.readLine()) != null) {
            System.out.println("\nLine " + lineCount + ": " + line);
            lineCount++;

            String[] parts = line.split(", ");
            String[] firstWords = parts[0].split(" ");
            int age = Integer.parseInt(firstWords[0]);
            String sex = firstWords[3];
            String species = firstWords[4];

            String[] secondWords = parts[1].split(" ");
            String season = secondWords.length > 2 ? secondWords[2] : "unknown";
            String color = parts[2];
            int weight = Integer.parseInt(parts[3].split(" ")[0]);
            String origin = parts[4] + ", " + parts[5];
            String arrivalDate = Utilities.arrivalDate();
            String birthDate = Utilities.genBirthDay(age, season);

            String name = "";
            String animalID = "";

            // Create the appropriate animal and add to its list
            switch (species.toLowerCase()) {
                case "hyena":
                    name = hyenaNames.remove(0);
                    animalID = "Hy" + String.format("%02d", Hyena.numOfHyena + 1);
                    Hyena hyena = new Hyena(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                    hyenaList.add(hyena);
                    break;
                case "lion":
                    name = lionNames.remove(0);
                    animalID = "Li" + String.format("%02d", Lion.numOfLion + 1);
                    Lion lion = new Lion(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                    lionList.add(lion);
                    break;
                case "tiger":
                    name = tigerNames.remove(0);
                    animalID = "Ti" + String.format("%02d", Tiger.numOfTigers + 1);
                    Tiger tiger = new Tiger(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                    tigerList.add(tiger);
                    break;
                case "bear":
                    name = bearNames.remove(0);
                    animalID = "Be" + String.format("%02d", Bear.numOfBears + 1);
                    Bear bear = new Bear(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                    bearList.add(bear);
                    break;
                default:
                    System.out.println("Unknown species: " + species);
                    break;
            }

            System.out.println("Assigned Name: " + name);
            System.out.println("Animal ID: " + animalID);
            System.out.println("Birthday: " + birthDate);
        }

    } catch (FileNotFoundException e) {
        System.out.println("❌ File not found: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("❌ Error reading file: " + e.getMessage());
    }

    // Print habitat summaries to console
    System.out.println("\n--- Zoo Population Summary ---");
    System.out.println("Hyenas: " + hyenaList.size());
    System.out.println("Lions: " + lionList.size());
    System.out.println("Tigers: " + tigerList.size());
    System.out.println("Bears: " + bearList.size());

    System.out.println("\n🐾 --- Zoo Population Organized by Habitat ---\n");

    System.out.println("Hyena Habitat:");
    for (Hyena h : hyenaList) {
        System.out.println(h.getFormattedData());
    }

    System.out.println("\nLion Habitat:");
    for (Lion l : lionList) {
        System.out.println(l.getFormattedData());
    }

    System.out.println("\nTiger Habitat:");
    for (Tiger t : tigerList) {
        System.out.println(t.getFormattedData());
    }

    System.out.println("\nBear Habitat:");
    for (Bear b : bearList) {
        System.out.println(b.getFormattedData());
    }

// ✅ Write report to zooPopulation.txt ONCE
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("zooPopulation.txt"))) {
        writer.write("🐾 --- Zoo Population Organized by Habitat ---\n\n");

        writer.write("Hyena Habitat:\n");
        for (Hyena h : hyenaList) {
            writer.write(h.getFormattedData() + "\n");
        }

        writer.write("\nLion Habitat:\n");
        for (Lion l : lionList) {
            writer.write(l.getFormattedData() + "\n");
        }

        writer.write("\nTiger Habitat:\n");
        for (Tiger t : tigerList) {
            writer.write(t.getFormattedData() + "\n");
        }

        writer.write("\nBear Habitat:\n");
        for (Bear b : bearList) {
            writer.write(b.getFormattedData() + "\n");
        }

        System.out.println("\n✅ zooPopulation.txt report generated successfully!");

    } catch (IOException e) {
        System.out.println("❌ Failed to write report: " + e.getMessage());
    }
    }


