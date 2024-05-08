package partPlanetas;

public class ResourceException extends Exception{
	public ResourceException(String mens) {
		super(mens);
	}
	public ResourceException() {
		System.out.println("No hay suficientes materiales");
	}
}
