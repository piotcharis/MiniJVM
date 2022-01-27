package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class FJump extends Instruction {
    private final int targetAddress;

    public FJump(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public void execute(Simulator simulator) {
        // Erstes Element nehmen
        int element = simulator.getStack().pop();

        // Falls es 0 ist, wird der programCounter zum Parameter gesetzt, sonst wird nichts gemacht.
        if (element == 0) {
            simulator.setProgramCounter(targetAddress);
        }
    }

    public int getParameter() {
        return targetAddress;
    }
}
