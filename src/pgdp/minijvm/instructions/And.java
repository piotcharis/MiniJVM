package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class And extends Instruction {

    public void execute(Simulator simulator) {
        // Erste zwei Elemente nehmen
        int element1 = simulator.getStack().pop();
        int element2 = simulator.getStack().pop();

        /*
         * Falls beide 0 sind, wird die 1 als Representation des true auf den Stack gelegt.
         * Sonst wird 0 als false draufgelegt.
         */
        if (element1 != 0 && element2 != 0) {
            simulator.getStack().push(1);
        } else {
            simulator.getStack().push(0);
        }
    }
}
