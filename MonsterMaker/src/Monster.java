import java.lang.reflect.Field;
import java.util.*;
class Monster {
    String eyesColor;
    String featherColor;
    boolean hasMagicalAbilities;
    int size;
    int strength;
    int durability;
    boolean weakness;
    int aggressionLevel;

    public Monster(String eyesColor, String featherColor, boolean hasMagicalAbilities,
                   int size, int strength, int durability, boolean weakness,
                   int aggressionLevel) {
        this.eyesColor = eyesColor;
        this.featherColor = featherColor;
        this.hasMagicalAbilities = hasMagicalAbilities;
        this.size = size;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    public Monster(Monster m) {
        this.eyesColor = m.eyesColor;
        this.featherColor = m.featherColor;
        this.hasMagicalAbilities = m.hasMagicalAbilities;
        this.size = m.size;
        this.strength = m.strength;
        this.durability = m.durability;
        this.weakness = m.weakness;
        this.aggressionLevel = m.aggressionLevel;
    }

    public void displayTraits() {
        System.out.println("Monster Traits");
        System.out.println("Eyes Color: " + eyesColor);
        System.out.println("Feather Color: " + featherColor);
        System.out.println("Magical Abilities: " + hasMagicalAbilities);
        System.out.println("Size: " + size);
        System.out.println("Strength: " + strength);
        System.out.println("Durability: " + durability);
        System.out.println("Weakness: " + weakness);
        System.out.println("Aggression Level: " + aggressionLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eyesColor, featherColor, hasMagicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Monster m)) return false;
        return Objects.equals(eyesColor, m.eyesColor) && Objects.equals(featherColor, m.featherColor) && Objects.equals(hasMagicalAbilities, m.hasMagicalAbilities) && Objects.equals(size, m.size) && Objects.equals(strength, m.strength) && Objects.equals(durability, m.durability) && Objects.equals(weakness, m.weakness) && Objects.equals(aggressionLevel, m.aggressionLevel);
    }


    public static List<Monster> createBabyMonsters(Monster p1, Monster p2) {
        Set<Monster> childrens = new HashSet<>();
        Class<?> monsterClass = Monster.class;

        Field[] fields = monsterClass.getDeclaredFields();

        for(int i=0;i<fields.length;i++) {
            try {
                // Get the trait values from the parents
                Object trait1 = fields[i].get(p1);
                Object trait2 = fields[i].get(p2);

                // Create a child with the combination of traits
                Monster c1 = new Monster(p1);
                Monster c2 = new Monster(p2);
                fields[i].setAccessible(true);
                fields[i].set(c1, getRandomTrait(trait1, trait2));
                fields[i].set(c2, getRandomTrait(trait1, trait2));

                // Add the child to the list
                childrens.add(c1);
                childrens.add(c2);
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return childrens.stream().toList();
    }
    private static Object getRandomTrait(Object trait1, Object trait2) {
        if(Math.random()*10 <= 5)
            return trait1;
        return trait2;
    }
}