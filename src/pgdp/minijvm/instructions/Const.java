package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Const extends Instruction {
    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    // Der Parameter wird auf den Stack gelegt.
    public void execute(Simulator simulator) {
        simulator.getStack().push(constant);
    }

    public int getParameter() {
        return constant;
    }
}
