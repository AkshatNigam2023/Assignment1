import java.util.*;

public class MonsterCreation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create Monster 1:");
        Monster p1 = createMonster(scanner);

        System.out.println();
        System.out.println("Create Monster 2:");
        Monster p2 = createMonster(scanner);

        List<Monster> babyMonsters = Monster.createBabyMonsters(p1, p2);

        System.out.println();
        System.out.println("Total child monsters created: "+babyMonsters.size());

        int cnt=1;
        for(Monster babyMonster: babyMonsters){
            System.out.println();
            System.out.println("Baby Monster Traits:" + cnt++);
            babyMonster.displayTraits();
        }
        scanner.close();
    }
    private static Monster createMonster(Scanner scanner) {
        System.out.print("Eye Color: ");
        String eyesColor = scanner.nextLine();

        System.out.print("Feather Color: ");
        String featherColor = scanner.nextLine();

        System.out.print("Magical Abilities (true/false): ");
        boolean hasMagicalAbilities = scanner.nextBoolean();

        System.out.print("Size: ");
        int size = scanner.nextInt();

        System.out.print("Strength: ");
        int strength = scanner.nextInt();

        System.out.print("Durability: ");
        int durability = scanner.nextInt();

        System.out.print("Weakness (true/false): ");
        boolean weakness = scanner.nextBoolean();

        System.out.print("Aggression Level: ");
        int aggressionLevel = scanner.nextInt();

        return new Monster(eyesColor, featherColor, hasMagicalAbilities, size,
                strength, durability, weakness, aggressionLevel);
    }
}