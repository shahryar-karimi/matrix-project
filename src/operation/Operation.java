package operation;

public abstract class Operation<T> {
    public abstract boolean isReadyToOperate();

    public abstract T operate() throws Exception;
}
