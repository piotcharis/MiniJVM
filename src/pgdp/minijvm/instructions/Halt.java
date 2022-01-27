package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Halt extends Instruction {

    public void execute(Simulator simulator) {
        // halted im Simulator wird auf true gesetzt.
        simulator.setHalted(true);
    }
}
