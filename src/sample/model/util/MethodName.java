package sample.model.util;

public enum MethodName {
    COMMON("Exact solution"),
    EULER("Euler method"),
    IMPROVED_EULER("Improved Euler method"),
    RUNGE_KUTTA("Runge-Kutta method");
    private String name;

    public String getName() {
        return name;
    }

    MethodName(String name) {
        this.name = name;
    }
}
