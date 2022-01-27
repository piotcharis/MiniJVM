package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Not extends Instruction {

    public void execute(Simulator simulator) {
        // Erstes Element nehmen.
        int element = simulator.getStack().pop();

        // Falls es 0 ist, wird 1(true), sonst 0(false) auf dem Stack gelegt.
        if (element == 0) {
            simulator.getStack().push(1);
        } else {
            simulator.getStack().push(0);
        }
    }
}
