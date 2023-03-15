package org.example;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcioMenu = -1;
        String[] botones = {" 1. ver gatos ", "2. ver favoritos ", " 3. salir "};

        do {
            String opcion = (String) JOptionPane.showInputDialog(null,  "Gatos Java", "Menu principal",
                    JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            for (int i =0; i < botones.length; i++){
                if (opcion.equals(botones[i])){
                    opcioMenu = i;
                }
            }
            switch (opcioMenu){
                case 0:
                    GatoService.verGatitos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatoService.verFavoritos(gato.getApikey());
                default:
                    break;
            }
        }while (opcioMenu != 1);
    }
}