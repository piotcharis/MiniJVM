package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Store extends Instruction {
    private final int stackAddress;

    public Store(int stackAddress) {
        this.stackAddress = stackAddress;
    }

    public void execute(Simulator simulator) {
        // Erstes Element nehmen und im gegebenen Index im Stack legen.
        int element = simulator.getStack().pop();
        simulator.getStack().setValueAtIndex(stackAddress + simulator.getStackOffset(), element);
    }

    public int getParameter() {
        return stackAddress;
    }
}
