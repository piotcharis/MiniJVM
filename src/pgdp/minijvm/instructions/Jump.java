package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Jump extends Instruction {
    private final int targetAddress;

    public Jump(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    // Der programCounter des Simulators wird auf dem Parameter gesetzt.
    public void execute(Simulator simulator) {
        simulator.setProgramCounter(targetAddress);
    }

    public int getParameter() {
        return targetAddress;
    }
}
