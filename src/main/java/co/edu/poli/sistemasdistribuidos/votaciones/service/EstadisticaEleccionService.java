package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.InfoGraficaBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;

public interface EstadisticaEleccionService {
    InfoGraficaBean generarEstadistica(EleccionEntity eleccion);
}
