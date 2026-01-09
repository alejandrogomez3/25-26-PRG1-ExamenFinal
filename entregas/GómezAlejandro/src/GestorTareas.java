import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorTareas {

    private static class Tarea {
        private String texto;
        private boolean hecha;

        public Tarea(String texto) {
            this.texto = texto;
            this.hecha = false;
        }

        public String getTexto() {
            return texto;
        }

        public boolean estaHecha() {
            return hecha;
        }

        public void completar() {
            hecha = true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Tarea> lista = new ArrayList<>();
        int opcion = 0;

        System.out.println("Gestor de Tareas v1.0");

        while (opcion != 5) {

            System.out.println("[1] Anadir tarea");
            System.out.println("[2] Marcar tarea como completada");
            System.out.println("[3] Ver tareas pendientes");
            System.out.println("[4] Ver estadisticas");
            System.out.println("[5] Salir");
            System.out.print("Opcion: ");

            while (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.print("Opcion: ");
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Descripcion de la nueva tarea: ");
                    String texto = sc.nextLine();
                    lista.add(new Tarea(texto));
                    System.out.println("Tarea anadida correctamente.");
                    break;

                case 2:
                    if (lista.isEmpty()) {
                        System.out.println("No hay tareas para marcar.");
                        break;
                    }

                    System.out.println("Tareas para Marcar");
                    for (int i = 0; i < lista.size(); i++) {
                        Tarea t = lista.get(i);
                        String estado = t.estaHecha() ? "Completada" : "Pendiente";
                        System.out.printf("%d. %s [%s]%n", i + 1, t.getTexto(), estado);
                    }

                    System.out.print("Numero de tarea a marcar como completada: ");
                    int indice = sc.nextInt();
                    sc.nextLine();

                    if (indice >= 1 && indice <= lista.size()) {
                        lista.get(indice - 1).completar();
                        System.out.println("Tarea marcada como completada.");
                    } else {
                        System.out.println("Numero de tarea invalido.");
                    }
                    break;

                case 3:
                    System.out.println("Tareas Pendientes");
                    int n = 1;
                    for (Tarea t : lista) {
                        if (!t.estaHecha()) {
                            System.out.printf("%d. %s%n", n, t.getTexto());
                            n++;
                        }
                    }
                    if (n == 1) {
                        System.out.println("No hay tareas pendientes.");
                    }
                    break;

                case 4:
                    int total = lista.size();
                    int hechas = 0;

                    for (Tarea t : lista) {
                        if (t.estaHecha()) {
                            hechas++;
                        }
                    }

                    int sinHacer = total - hechas;
                    double porcentaje = total == 0 ? 0 : (hechas * 100.0 / total);

                    System.out.println("Estadisticas");
                    System.out.println("Total de tareas: " + total);
                    System.out.println("Tareas completadas: " + hechas);
                    System.out.println("Tareas pendientes: " + sinHacer);
                    System.out.println("Porcentaje de completacion: " + porcentaje + "%");
                    break;

                case 5:
                    System.out.println("Saliendo del gestor de tareas.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }

        sc.close();
    }
}
