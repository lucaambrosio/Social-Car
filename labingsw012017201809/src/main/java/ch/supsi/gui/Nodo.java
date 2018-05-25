package ch.supsi.gui;

import java.util.Objects;

public class Nodo {
    protected Coordinata coordinata;

    public Nodo(Coordinata coordinata) {
        this.coordinata = coordinata;
    }

    public Coordinata getCoordinata() {
        return coordinata;
    }

    public void scriviNodo(){
        coordinata.scriviCoordinata();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(coordinata, nodo.coordinata);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coordinata);
    }
}
