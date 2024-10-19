package com.mobdeve.s20.teves.hannah.mco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharData {
    String name;
    int charImg;

    // Requirements as a Map<String, Integer> for both ascension and talent requirements
    Map<String, Integer> ascensionRequirements; // Keys are material/enemy drop names, values are counts
    Map<String, Integer> talentRequirements; // Keys are book/enemy drop names, values are counts
    int ascensionMora; // Mora cost for ascension
    int talentMora; // Mora cost for talent upgrades
    List<String> bestArtifactSets; // Best artifact sets for the character
    List<String> bestWeapons; // Best weapons for the character
    List<String> skillPrio; // Skill priority for the character

    // Optimized constructor
    public CharData(String name,
                    int charImg,
                    Map<String, Integer> ascensionRequirements,
                    int ascensionMora,
                    Map<String, Integer> talentRequirements,
                    int talentMora,
                    List<String> bestArtifactSets,
                    List<String> bestWeapons,
                    List<String> skillPrio) {
        this.name = name;
        this.charImg = charImg;
        this.ascensionRequirements = ascensionRequirements;
        this.ascensionMora = ascensionMora;
        this.talentRequirements = talentRequirements;
        this.talentMora = talentMora;
        this.bestArtifactSets = bestArtifactSets;
        this.bestWeapons = bestWeapons;
        this.skillPrio = skillPrio;
    }

    public int getCharImg() {
        return charImg;
    }

    // Method to get a formatted string of ascension materials
    public String getAscensionMaterials() {
        StringBuilder materials = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ascensionRequirements.entrySet()) {
            materials.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        materials.append("Total Mora: ").append(ascensionMora);
        return materials.toString();
    }

    // Method to get a formatted string of talent materials
    public String getTalentMaterials() {
        StringBuilder materials = new StringBuilder();
        for (Map.Entry<String, Integer> entry : talentRequirements.entrySet()) {
            materials.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        materials.append("Total Mora: ").append(talentMora);
        return materials.toString();
    }

    // Sample data
    public static List<CharData> getCharacterData() {
        List<CharData> charList = new ArrayList<>();

        // Create a map for ascension requirements for Aether
        Map<String, Integer> aetherAscension = new HashMap<>();
        aetherAscension.put("Silver", 1);
        aetherAscension.put("FragmentName1", 2);
        aetherAscension.put("ChunkName1", 3);
        aetherAscension.put("EnemyDrop1", 1);
        aetherAscension.put("EnemyDrop2", 2);
        aetherAscension.put("EnemyDrop3", 3);
        aetherAscension.put("RegionMats", 1);
        aetherAscension.put("BossDrops", 1);

        // Create a map for talent requirements for Aether
        Map<String, Integer> aetherTalents = new HashMap<>();
        aetherTalents.put("Crown", 1);
        aetherTalents.put("BookBrown", 1);
        aetherTalents.put("BookSilver", 2);
        aetherTalents.put("BookGold", 3);
        aetherTalents.put("EnemyDrop1_Talent", 1);
        aetherTalents.put("EnemyDrop2_Talent", 2);
        aetherTalents.put("EnemyDrop3_Talent", 3);
        aetherTalents.put("WeeklyBossDrops", 1);

        // Create lists for best artifacts, best weapons, and skill priority
        List<String> aetherBestArtifacts = new ArrayList<>();
        aetherBestArtifacts.add("Artifact Set 1");
        aetherBestArtifacts.add("Artifact Set 2");

        List<String> aetherBestWeapons = new ArrayList<>();
        aetherBestWeapons.add("Weapon 1");
        aetherBestWeapons.add("Weapon 2");

        List<String> aetherSkillPrio = new ArrayList<>();
        aetherSkillPrio.add("Skill A");
        aetherSkillPrio.add("Skill B");

        // Add Aether to character list
        charList.add(new CharData("Aether", R.drawable.sample_character, aetherAscension, 5000, aetherTalents, 3000,
                aetherBestArtifacts, aetherBestWeapons, aetherSkillPrio));

        // Character 2
        Map<String, Integer> character2Ascension = new HashMap<>();
        character2Ascension.put("Silver2", 1);
        character2Ascension.put("FragmentName2", 2);
        character2Ascension.put("ChunkName2", 3);
        character2Ascension.put("EnemyDrop1_Char2", 1);
        character2Ascension.put("EnemyDrop2_Char2", 2);
        character2Ascension.put("EnemyDrop3_Char2", 3);
        character2Ascension.put("RegionMats2", 1);
        character2Ascension.put("BossDrops2", 1);

        Map<String, Integer> character2Talents = new HashMap<>();
        character2Talents.put("Crown2", 1);
        character2Talents.put("BookBrown2", 1);
        character2Talents.put("BookSilver2", 2);
        character2Talents.put("BookGold2", 3);
        character2Talents.put("EnemyDrop1_Talent2", 1);
        character2Talents.put("EnemyDrop2_Talent2", 2);
        character2Talents.put("EnemyDrop3_Talent2", 3);
        character2Talents.put("WeeklyBossDrops2", 1);

        // Create lists for best artifacts, best weapons, and skill priority for Character 2
        List<String> character2BestArtifacts = new ArrayList<>();
        character2BestArtifacts.add("Artifact Set 3");
        character2BestArtifacts.add("Artifact Set 4");

        List<String> character2BestWeapons = new ArrayList<>();
        character2BestWeapons.add("Weapon 3");
        character2BestWeapons.add("Weapon 4");

        List<String> character2SkillPrio = new ArrayList<>();
        character2SkillPrio.add("Skill C");
        character2SkillPrio.add("Skill D");

        // Add Character 2 to character list
        charList.add(new CharData("Character 2", R.drawable.sample_character, character2Ascension, 4000, character2Talents, 2500,
                character2BestArtifacts, character2BestWeapons, character2SkillPrio));

        // Character 3
        Map<String, Integer> character3Ascension = new HashMap<>();
        character3Ascension.put("Silver3", 1);
        character3Ascension.put("FragmentName3", 2);
        character3Ascension.put("ChunkName3", 3);
        character3Ascension.put("EnemyDrop1_Char3", 1);
        character3Ascension.put("EnemyDrop2_Char3", 2);
        character3Ascension.put("EnemyDrop3_Char3", 3);
        character3Ascension.put("RegionMats3", 1);
        character3Ascension.put("BossDrops3", 1);

        Map<String, Integer> character3Talents = new HashMap<>();
        character3Talents.put("Crown3", 1);
        character3Talents.put("BookBrown3", 1);
        character3Talents.put("BookSilver3", 2);
        character3Talents.put("BookGold3", 3);
        character3Talents.put("EnemyDrop1_Talent3", 1);
        character3Talents.put("EnemyDrop2_Talent3", 2);
        character3Talents.put("EnemyDrop3_Talent3", 3);
        character3Talents.put("WeeklyBossDrops3", 1);

        // Create lists for best artifacts, best weapons, and skill priority for Character 3
        List<String> character3BestArtifacts = new ArrayList<>();
        character3BestArtifacts.add("Artifact Set 5");
        character3BestArtifacts.add("Artifact Set 6");

        List<String> character3BestWeapons = new ArrayList<>();
        character3BestWeapons.add("Weapon 5");
        character3BestWeapons.add("Weapon 6");

        List<String> character3SkillPrio = new ArrayList<>();
        character3SkillPrio.add("Skill E");
        character3SkillPrio.add("Skill F");

        // Add Character 3 to character list
        charList.add(new CharData("Character 3", R.drawable.sample_character, character3Ascension, 4000, character3Talents, 2500,
                character3BestArtifacts, character3BestWeapons, character3SkillPrio));

        return charList;
    }
}
