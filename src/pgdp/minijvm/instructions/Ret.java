package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Ret extends Instruction {

    public void execute(Simulator simulator) {
        // Der StackPointer wird wieder mittels dem stackOffset vor dem Anfang des Stackframes gesetzt.
        simulator.getStack().setStackPointer(simulator.getStackOffset() - 1);

        // Der StackOffset und ProgramCounter werden mittels pop wieder
        // auf die gespeicherten Werte vor dem Call gesetzt.
        simulator.setStackOffset(simulator.getStack().pop());
        simulator.setProgramCounter(simulator.getStack().pop());
    }
}
