package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Alloc extends Instruction {
    private final int count;

    public Alloc(int count) {
        this.count = count;
    }

    public void execute(Simulator simulator) {
        // Verwenden von alloc in Stack
        simulator.getStack().alloc(count);
    }

    public int getParameter() {
        return count;
    }
}
