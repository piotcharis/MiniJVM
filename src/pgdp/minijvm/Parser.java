package pgdp.minijvm;

import pgdp.minijvm.instructions.*;

import java.util.ArrayList;
import java.util.Arrays;

public final class Parser {

    private Parser() {
    }

    public static Instruction[] instructionsFromString(String program) {

        // Jede Zeile wird getrennt.
        String[] instructions = program.split("\n");

        // Kommentare werden entfernt.
        instructions = Arrays.stream(instructions).filter(c -> !c.startsWith(";")).toArray(String[]::new);

        ArrayList<Instruction> result = new ArrayList<>();

        // Jede Zeile wird bearbeitet und in result als Instruction addiert.
        for (int i = 0; i < instructions.length; i++) {

            // Switch um rauszufinden welche Instruction man hat.
            switch (instructions[i]) {
                case "ADD" -> result.add(new Add());
                case "SUB" -> result.add(new Sub());
                case "LESS" -> result.add(new Less());
                case "AND" -> result.add(new And());
                case "NOT" -> result.add(new Not());
                case "RET" -> result.add(new Ret());
                case "HALT" -> result.add(new Halt());
                // Fälle, wo es ein Label oder Adresse gibt, es wird der Index des Labels/Adresse
                // gefunden und im Instruction als Parameter gegeben.
                default -> {
                    if (instructions[i].startsWith("CONST")) {
                        result.add(new Const(findParameter(6, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("ALLOC")) {
                        result.add(new Alloc(findParameter(6, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("LOAD")) {
                        result.add(new Load(findParameter(5, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("STORE")) {
                        result.add(new Store(findParameter(6, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("JUMP")) {
                        result.add(new Jump(findParameter(5, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("FJUMP")) {
                        result.add(new FJump(findParameter(6, instructions[i], instructions)));
                        break;
                    }
                    if (instructions[i].startsWith("CALL")) {
                        result.add(new Call(findParameter(5, instructions[i], instructions)));
                        break;
                    }
                }
            }
        }
        return result.toArray(new Instruction[0]);
    }

    // Findet den gesuchten label im Program und gibt den Index zurück minus alle andere Labels,
    // die nicht im Instruction Array sein werden, falls der label nicht existiert wird -1
    // zurückgegeben, was zu einem Abstürz führen wird.
    private static int findLabelIndex(String[] instructions, String label) {
        int labelCounter = 0;

        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i].endsWith(":") && instructions[i].contains(label)) {
                return i - labelCounter;
            } else if (instructions[i].endsWith(":")) {
                labelCounter++;
            }
        }
        return -1;
    }

    // Falls der Label/Adresse schon ein Int ist, wird es zurückgegeben, sonst wird mit der findLabel Methode
    // der Index gefunden.
    private static int findParameter(int i, String currentWord, String[] instructions) {
        if (Character.isDigit(currentWord.charAt(i)) ||
                currentWord.charAt(i) == '-' && Character.isDigit(currentWord.charAt(i + 1))) {
            return Integer.parseInt(currentWord.substring(i));
        } else {
            return findLabelIndex(instructions, currentWord.substring(i));
        }
    }
}
