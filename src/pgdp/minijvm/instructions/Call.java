package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Call extends Instruction {
    private final int functionPointer;

    public Call(int functionPointer) {
        this.functionPointer = functionPointer;
    }

    public void execute(Simulator simulator) {
        // Der ProgramCounter und StackOffset werden im Stack gespeichert.
        simulator.getStack().push(simulator.getProgramCounter());
        simulator.getStack().push(simulator.getStackOffset());

        // Der ProgramCounter wird zum übergegebenen Parameter gesetzt und der StackOffset
        // zum aktuellen StackPointer, damit man weiß, wo der Stackframe beginnt.
        simulator.setProgramCounter(functionPointer);
        simulator.setStackOffset(simulator.getStack().getStackPointer() + 1);
    }

    public int getParameter() {
        return functionPointer;
    }
}
