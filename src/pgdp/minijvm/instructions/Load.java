package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Load extends Instruction {
    private final int stackAddress;

    public Load(int stackAddress) {
        this.stackAddress = stackAddress;
    }

    public void execute(Simulator simulator) {
        // Den Wert des Elementes am gegebenen Index finden und auf dem Stack legen.
        int element = simulator.getStack().getValueAtIndex(stackAddress + simulator.getStackOffset());
        simulator.getStack().push(element);
    }

    public int getParameter() {
        return stackAddress;
    }
}
